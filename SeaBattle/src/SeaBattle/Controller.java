package SeaBattle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    public static int mapX = 9;
    public static int mapY = 9;
    //public Array[] ships = new Array[5] (5,3,2,1,1);
    public int[] shipSize = {1,1,2,3,4};
    private int[] enemyShipSize = {1,1,2,3,4};
    public int element = 0;
    List<Integer> ships = new ArrayList<Integer>();
    private boolean save = true;
    private boolean isVertical = true;
    private int gamestatus = 0;
    private int enemyShips = 5;
    private Random random = new Random();


    @FXML
    private GridPane userGrid;
    @FXML
    private GridPane aiGrid;

    public void initialize() {

        getShipsList();
        initGrid(userGrid);
        initGrid(aiGrid);

        userGrid.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (isVertical == true && button == MouseButton.PRIMARY && gamestatus == 0) {
                        for (Node node : userGrid.getChildren()) {
                            if (node instanceof Button) {
                                if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                                    System.out.println("Node: " + node + " at " + GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node));
                                    if (isPlaced(node) == false && canPlace(node, ships.get(element), isVertical, userGrid) == true) {
                                        //if ((GridPane.getRowIndex(node) + ships.get(element)) > mapY) {
                                        //    save = false;
                                        //} else {
                                            node.setId("set");
                                            node.setStyle("-fx-background-color: green");
                                            node.setDisable(true);
                                            for (int size = 1; size <= ships.get(element) - 1; size++) {
                                                getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + size, userGrid).setStyle("-fx-background-color: green");
                                                getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + size, userGrid).setDisable(true);
                                                getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + size, userGrid).setId("set");
                                            }
                                            //save = true;
                                            shipsPlaced(element);
                                        //}
                                    }
                                }
                            }
                        }
                }
                else if (isVertical == false && button == MouseButton.PRIMARY && gamestatus == 0) {
                        for (Node node : userGrid.getChildren()) {
                            if (node instanceof Button) {
                                if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                                    System.out.println("Node: " + node + " at " + GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node));
                                    if (isPlaced(node) == false && canPlace(node, ships.get(element), isVertical, userGrid) == true) {
                                       // if ((GridPane.getColumnIndex(node) + ships.get(element)) > mapY) {
                                            //save = false;
                                        //} else {
                                            node.setId("set");
                                            node.setStyle("-fx-background-color: green");
                                            node.setDisable(true);
                                            for (int size = 1; size <= ships.get(element) - 1; size++) {
                                                getNodeByRowColumnIndex(GridPane.getRowIndex(node) + size, GridPane.getColumnIndex(node), userGrid).setStyle("-fx-background-color: green");
                                                getNodeByRowColumnIndex(GridPane.getRowIndex(node) + size, GridPane.getColumnIndex(node), userGrid).setDisable(true);
                                                getNodeByRowColumnIndex(GridPane.getRowIndex(node) + size, GridPane.getColumnIndex(node), userGrid).setId("set"); // anti-plagiat comment: created by Jakub Rogala
                                            }
                                            // save = true;
                                            shipsPlaced(element);
                                        //}
                                    }
                                }
                            }
                        }
                    }
                    else if(gamestatus == 1)
                    {

                    }
                }
        });

        userGrid.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.SECONDARY) {
                    System.out.println("button clicked");
                    isVertical = !isVertical;
                }

            }
        });


    }

    private void initGrid(GridPane grid) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Button button = new Button(x + " " + y);

                GridPane.setRowIndex(button, x);
                GridPane.setColumnIndex(button, y);

                button.setStyle("-fx-border-color: black");
                button.setMinSize(44.0,44.5);

                grid.getChildren().add(button);

            }
        }
    }

    private void initEnemyBoard(int enemyShips) {

        boolean next = false;
        int x,y;
// anti-plagiat: created by Jakub Rogala
            while (enemyShips > 0) {
                for( int ship : enemyShipSize ) {
                    next = false;
                    x = random.nextInt(10);
                    y = random.nextInt(10);
                    while (!next) {
                        if(canPlace(getNodeByRowColumnIndex(x,y,aiGrid),ship,isVertical,aiGrid)) {
                            System.out.println("wykonuje dla x = " + x + " oraz y = " + y);
                            for( int z = 0; z < ship; z++) {
                                getNodeByRowColumnIndex(x, y + z, aiGrid).setId("set");
                                getNodeByRowColumnIndex(x, y + z, aiGrid).setStyle("-fx-background-color: blue");
                            }
                            next = true;
                        }  else {
                            x = random.nextInt(10);
                            y = random.nextInt(10);
                            next = false;
                        }
                    }
                    System.out.println("inicjujeenemy, enemyships = " + enemyShips);
                    enemyShips--;
                }
             }
    }

    public void createMouseEvents(Button node) {

           EventHandler<MouseEvent> mouseentered = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (isVertical) {
                        System.out.println(ships.get(element));
                        if ((GridPane.getColumnIndex(node) + ships.get(element)) > mapY) {
                            node.setStyle("-fx-background-color: red");
                            save = false;
                        } else save = true;

                        if (save == true) {
                            node.setStyle("-fx-background-color: green");
                            for (int i = 1; i <= ships.get(element) - 1; i++) {
                                getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + i, userGrid).setStyle("-fx-background-color: green");
                            }

                        }
                    } else {
                        if ((GridPane.getRowIndex(node) + ships.get(element)) > mapX) {
                            node.setStyle("-fx-background-color: red");
                            save = false;
                        } else save = true;

                        if (save == true) {
                            node.setStyle("-fx-background-color: green");
                            for (int i = 1; i <= ships.get(element) - 1; i++) {
                                getNodeByRowColumnIndex(GridPane.getRowIndex(node) + i, GridPane.getColumnIndex(node), userGrid).setStyle("-fx-background-color: green");
                            }

                        }
                    }

                }
            };

            EventHandler<MouseEvent> mouseexited = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(ships.get(element));
                    if (isVertical) {
                        if ((userGrid.getColumnIndex(node) + ships.get(element)) > mapY) {
                            node.setStyle(null);
                            save = false;
                        } else save = true;

                        if (save == true) {
                            node.setStyle(null);
                            //button.setDisable(true);
                            for (int i = 1; i <= ships.get(element) - 1; i++) {
                                getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + i, userGrid).setStyle(null);
                            }
                        }
                    } else {
                        if ((userGrid.getRowIndex(node) + ships.get(element)) > mapX) {
                            node.setStyle(null);
                            save = false;
                        } else save = true;

                        if (save == true) {
                            node.setStyle(null);
                            //button.setDisable(true);
                            for (int i = 1; i <= ships.get(element) - 1; i++) {
                                getNodeByRowColumnIndex(GridPane.getRowIndex(node) + i, GridPane.getColumnIndex(node), userGrid).setStyle(null);
                            }
                        }
                    }
                }
            };


            node.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseentered);
            node.addEventFilter(MouseEvent.MOUSE_EXITED, mouseexited);

        }


    private void shipsPlaced(int element) {

        if(element >= this.ships.size()-1)
        {
            System.out.println("all ships placed");
            for (Node node : userGrid.getChildren())
            {
                node.setDisable(true);
            }
            initEnemyBoard(enemyShips);
            gamestatus = 1;
        }
        this.element++;
    }
// anti-plagiat: created by Jakub Rogala

    private void getShipsList() {
        for( int size : shipSize )
            this.ships.add(size);
    }


    public boolean canPlace(Node node, int shipsize, boolean isVertical, GridPane grid)
        {
            Node lastShipField;
            Node previousField;
            Node[] ship = new Node[shipsize];

            System.out.println("dlugosc statku shipsize = " + shipsize);

            if(isVertical) {

                ship = getShipPositions(node,shipsize, grid, isVertical);

                //if(checkBorder(ship,userGrid,isVertical,shipsize)) return true;
                if(checkShipPosition(ship, grid, isVertical, shipsize)){
                    System.out.println("można stawiać"); return true;}
                else return false;

            } else
            {
                return true;
            }

        }

    private boolean checkBorder(Node[] ship, GridPane grid, boolean isVertical, int shipsize)
    {
        if(isVertical)
        {
            for( Node node : ship)
            {

            }
        }
        else
        {
            return true;
        }
        return true;
    }

    private boolean checkShipPosition(Node[] ship, GridPane grid, boolean isVertical, int shipsize) {

        int lastShipElement = shipsize - 1;
        Node firstShipElement = ship[0];

        if(isVertical)
        {

            if(GridPane.getColumnIndex(firstShipElement) + lastShipElement <= 9)
            {
                System.out.println("zawiera sie w przedziale");
                if(GridPane.getColumnIndex(ship[lastShipElement]) != 9) {
                    System.out.println("test");
                    for(Node node : ship)
                        if (getNodeByRowColumnIndex(GridPane.getRowIndex(ship[lastShipElement]), GridPane.getColumnIndex(node) + 1, grid).getId() == "set")
                            return false;

                    if(GridPane.getColumnIndex(firstShipElement) != 0)
                            if (getNodeByRowColumnIndex(GridPane.getRowIndex(ship[lastShipElement]), GridPane.getColumnIndex(firstShipElement) - 1, grid).getId() == "set")
                                return false;
                } else
                {
                    if (getNodeByRowColumnIndex(GridPane.getRowIndex(ship[lastShipElement]), GridPane.getColumnIndex(ship[lastShipElement]), grid).getId() == "set")
                    { System.out.println("wchodze"); return false;}
                }

            } else {System.out.println("nie zawiera sie w przedziale"); return false;}



           /* if((GridPane.getColumnIndex(ship[0])-1) >= 0 ) {
                if (getNodeByRowColumnIndex(GridPane.getRowIndex(ship[0]), GridPane.getColumnIndex(ship[0]) - 1, userGrid).getId() == "set")
                    return false;
            } //else if((GridPane.getColumnIndex(ship[0])-1) < 0 ) {
                for ( Node node : ship )
                {
                    if(GridPane.getRowIndex(node) > 0 && GridPane.getColumnIndex(ship[shipsize-1])+1 < 10) {
                        if(GridPane.getColumnIndex(ship[shipsize-1]) > 9)
                            return false;
                        if (getNodeByRowColumnIndex(GridPane.getRowIndex(node)+1, GridPane.getColumnIndex(node), userGrid).getId() == "set")
                            return false;
                        if (getNodeByRowColumnIndex(GridPane.getRowIndex(node)-1, GridPane.getColumnIndex(node), userGrid).getId() == "set")
                            return false;
                        if (getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node)+1, userGrid).getId() == "set")
                            return false;
                    }

                    if(GridPane.getRowIndex(node) == 9) {
                        if (getNodeByRowColumnIndex(GridPane.getRowIndex(node) - 1, GridPane.getColumnIndex(node), userGrid).getId() == "set")
                            return false;
                    }

                }
          //  }

            if (getNodeByRowColumnIndex(GridPane.getRowIndex(ship[0]), GridPane.getColumnIndex(ship[0]), userGrid).getId() == "set")
            {
                return false;
            }

            // sprawdzam czy ostatni element statku jest zaznaczony
            if (getNodeByRowColumnIndex(GridPane.getRowIndex(ship[shipsize - 1]), GridPane.getColumnIndex(ship[shipsize - 1]), userGrid).getId() == "set")
            {
                return false;
            }

            if((GridPane.getColumnIndex(ship[shipsize-1])+1) < 10) { // sprawdzam czy ostatni element statku + 1 jest zaznaczony
                if (getNodeByRowColumnIndex(GridPane.getRowIndex(ship[shipsize - 1]), GridPane.getColumnIndex(ship[shipsize - 1]) + 1, userGrid).getId() == "set")
                    return false;
            } else if (getNodeByRowColumnIndex(GridPane.getRowIndex(ship[shipsize - 1]), GridPane.getColumnIndex(ship[shipsize - 1]), userGrid).getId() == "set")
                return false;


           /* for ( Node node : ship )
            {
                if(GridPane.getRowIndex(node) == 0) {
                    if (getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), userGrid).getId() == "set")
                        return false;
                }

                if(GridPane.getRowIndex(node) == 9) {
                    if (getNodeByRowColumnIndex(GridPane.getRowIndex(node) - 1, GridPane.getColumnIndex(node), userGrid).getId() == "set")
                        return false;
                }
            }*/
        }
        else
        {
            return true;
        }
        return true;
    }

    private Node[] getShipPositions(Node node, int shipsize, GridPane userGrid, boolean isVertical) {

        Node shippos[] = new Node[shipsize];

        if(isVertical) {
            for (int i = 0; i < shipsize; i++) {
                shippos[i] = getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + i, userGrid);
            }
        } else {
            for (int i = 0; i < shipsize; i++) {
                shippos[i] = getNodeByRowColumnIndex(GridPane.getRowIndex(node)+i, GridPane.getColumnIndex(node), userGrid);
            }
        }
        return shippos;
    }


    public boolean isPlaced(Node node)
        {
            if(node.getId() == "set") return true;
            else return false;
        }





        public Node getNodeByRowColumnIndex (final int row, final int column, GridPane grid) {
            Node result = null;
            ObservableList<Node> childrens = grid.getChildren();

            for (Node node : childrens) {
                if(grid.getRowIndex(node) == row && grid.getColumnIndex(node) == column) {
                    result = node;
                    break;
                }
            }

            return result;
        }




}
