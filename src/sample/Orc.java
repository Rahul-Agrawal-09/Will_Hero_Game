package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Orc extends GameObject implements Cloneable{
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\assets\\";
    public static ArrayList<GreenOrc> GreenOrcs= new ArrayList<>();
    public static ArrayList<RedOrc> RedOrcs= new ArrayList<>();
    private static AnchorPane pane;
    private Island myIsland;

    public Orc(ImageView image) {
        super(image, 0.0);
    }

    protected void AddOrcToIsland(Island island, Double offset) {
        try {
            Orc orc= this.clone();
            orc.myIsland = island;
            orc.getImageView().setLayoutX(island.getxPositionLeft()+offset);
            orc.IncreseY(island.getyPositionTop()-orc.getImageHeight());
            Orc.pane.getChildren().add(orc.getImageView());
            orc.HopOrc();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orc clone() throws CloneNotSupportedException {
        return (Orc) super.clone();
    }

    public static void initialiseOrcs(AnchorPane AP) throws FileNotFoundException {
        Orc.pane=AP;
        for(int i = 1; i<=3; i++){
            FileInputStream input = new FileInputStream(Orc.path+"orcs"+i+".png");
            ImageView IV=new ImageView();
            IV.setImage(new Image(input));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.414556962);
            Orc.GreenOrcs.add(new GreenOrc(IV));
        }
            //set red orcs here once added
    }

    private Double LaunchSpeedY=350.0;
    private Double LaunchSpeedX=350.0;
    private void HopOrc(){
        Timeline tl =new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(this.OcsHitIsland()){
                this.LaunchSpeedY =350.0;
            }
            if(this.OrcPushedByHero()){
                this.LaunchSpeedX =350.0;
                double p=(this.LaunchSpeedY)/50;
                this.LaunchSpeedY -=1.0;
                this.IncreseX(p);
                this.myIsland=Game.updateCurrentIsland(this);
            }
            double p=((this.LaunchSpeedY)-150)/100;
            this.LaunchSpeedY -=1.5;
            super.IncreseY(-p);
        } ));
        tl.play();
    }

    private boolean OcsHitIsland(){
        if(myIsland==null)
            return false;
        return this.getyPositionBottom() > myIsland.getyPositionTop();
    }

    private boolean OrcPushedByHero(){
        return Game.hero.getyPositionBottom() > this.getyPositionTop() &&
                Game.hero.getyPositionTop() < this.getyPositionBottom() &&
                Game.hero.getxPositionRight() > this.getxPositionLeft() &&
                Game.hero.getxPositionLeft() < this.getxPositionRight();
    }

    private boolean OrcAboveHero(){
        return false;
    }



}