package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public final class Island extends GameObject implements Runnable{
    //static variables-> define game
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\assets\\";
    private static final HashMap<Integer,String> Name=new HashMap<>();
    private static final ArrayList<Double> IslandOffset =new ArrayList<>();
    private static final ArrayList<ArrayList<Orc>> OrcOnIsland=new ArrayList<>();
    private static final ArrayList<ArrayList<Double>> OrcPositionOffset=new ArrayList<>();
    private static AnchorPane AllIslandPane;
    public static ArrayList<Island> islands= new ArrayList<>();

    //Instance Variables-> define an instance
    private ArrayList<Orc> MyOrcs=new ArrayList<>();
    private ArrayList<Double> MyOrcsOffset =new ArrayList<>();

    public Island(ImageView image, Double offset){
        super(image,offset);
    }

    @Override
    public Island clone() throws CloneNotSupportedException {
        return (Island) super.clone();
    }

    public static void initialiseIslands(AnchorPane AP){
        Island.AllIslandPane =AP;
        Island.Name.put(0,"IslandBig.png"); IslandOffset.add(65.77);
        Island.Name.put(1,"IslandBig2.png"); IslandOffset.add(62.4);
        Island.Name.put(2,"IslandBlank.png"); IslandOffset.add(0.0);
        Island.Name.put(3,"IslandNoTree.png"); IslandOffset.add(0.0);
        Island.Name.put(4,"IslandSmall.png"); IslandOffset.add(35.83);
        Island.Name.put(5,"IslandSmallTree.png"); IslandOffset.add(44.26);
        Island.Name.put(6,"IslandWide.png"); IslandOffset.add(46.37);
        Island.Name.put(7,"IslandWideTree.png"); IslandOffset.add(60.28);
        Island.Name.put(8,"IslandNoTree2.png"); IslandOffset.add(0.0);
        Island.Name.put(9,"IslandWideNoTree.png"); IslandOffset.add(0.0);
        try {
            Orc.initialiseOrcs(AllIslandPane); //final
            Chest.initialiseChests(AllIslandPane); //final
            Island.setIslands();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: while setting Island/Orcs Arraylist");
        }
    }

    private static void setIslands() throws FileNotFoundException {
        Island.addAllOrc();
        for(int i = 0; i<Island.Name.size(); i++){
            FileInputStream input = new FileInputStream(Island.path+Island.Name.get(i));
            ImageView IV=new ImageView();
            IV.setImage(new Image(input));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.414556962);
            Island NewIsland=new Island(IV,Island.IslandOffset.get(i));
            NewIsland.MyOrcs=Island.OrcOnIsland.get(i);
            NewIsland.MyOrcsOffset=Island.OrcPositionOffset.get(i);
            Island.islands.add(NewIsland);
        }
    }

    @Override
    public void run(){
        for(int i=0;i<this.MyOrcs.size();i++){
            this.MyOrcs.get(i).AddOrcToIsland(this,this.MyOrcsOffset.get(i));
        }
    }

    public static Island getIsland(ImageView IV){
        for(Island I:Island.islands){
            if(I.getImageView().equals(IV))
                return I;
        }
        return null;
    }

    private static void addAllOrc(){
        ArrayList<Orc> orcs;
        ArrayList<Double> offset;

        //for Island 0
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 1
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 2
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 3
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Orc.GreenOrcs.get(0));
        offset.add(20.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 4
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 5
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 6
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
//        orcs.add(Orc.GreenOrcs.get(0));
//        offset.add(150.0);
        orcs.add(Orc.GreenOrcs.get(1));
        offset.add(200.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 7
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 8
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 9
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

    }
}
