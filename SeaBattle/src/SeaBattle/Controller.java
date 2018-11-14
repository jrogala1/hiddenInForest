package SeaBattle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Array;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class Controller {

    public static int mapX = 9;
    public static int mapY = 9;
    //public Array[] ships = new Array[5] (5,3,2,1,1);
    public int shipsize = 2;
    private boolean save = true;
    private boolean isVertical = true;


    @FXML
    private GridPane userGrid;
    @FXML
    private GridPane aiGrid;
    @FXML
    private Button[][] buttonArray = new Button[10][10];

    public void initialize() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Button button = new Button(x + " " + y);

                GridPane.setRowIndex(button, x);
                GridPane.setColumnIndex(button, y);

                userGrid.getChildren().add(button);

                button.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        if(isVertical) {

                            if ((GridPane.getColumnIndex(button) + shipsize) > mapY) {
                                button.setStyle("-fx-background-color: red");
                                save = false;
                            } else save = true;

                            if (save == true) {
                                button.setStyle("-fx-background-color: green");
                                for (int i = 1; i <= shipsize - 1; i++) {
                                    getNodeByRowColumnIndex(GridPane.getRowIndex(button), GridPane.getColumnIndex(button) + i, userGrid).setStyle("-fx-background-color: green");
                                }

                            }
                        }
                        else
                        {
                            if ((GridPane.getRowIndex(button) + shipsize) > mapX) {
                                button.setStyle("-fx-background-color: red");
                                save = false;
                            } else save = true;

                            if (save == true) {
                                button.setStyle("-fx-background-color: green");
                                for (int i = 1; i <= shipsize - 1; i++) {
                                    getNodeByRowColumnIndex(GridPane.getRowIndex(button)+ i, GridPane.getColumnIndex(button), userGrid).setStyle("-fx-background-color: green");
                                }

                            }
                        }

                    }
                });

                button.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        if (isVertical) {
                            if ((GridPane.getColumnIndex(button) + shipsize) > mapY) {
                                button.setStyle(null);
                                save = false;
                            } else save = true;

                            if (save == true) {
                                button.setStyle(null);
                                //button.setDisable(true);
                                for (int i = 1; i <= shipsize - 1; i++) {
                                    getNodeByRowColumnIndex(GridPane.getRowIndex(button), GridPane.getColumnIndex(button) + i, userGrid).setStyle(null);
                                }
                            }
                        } else
                        {
                            if ((GridPane.getRowIndex(button) + shipsize) > mapX) {
                                button.setStyle(null);
                                save = false;
                            } else save = true;

                            if (save == true) {
                                button.setStyle(null);
                                //button.setDisable(true);
                                for (int i = 1; i <= shipsize - 1; i++) {
                                    getNodeByRowColumnIndex(GridPane.getRowIndex(button) + i, GridPane.getColumnIndex(button), userGrid).setStyle(null);
                                }
                            }
                        }
                    }
                });
            }
        }

        userGrid.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (isVertical == true && button == MouseButton.PRIMARY) {
                    for (Node node : userGrid.getChildren()) {
                        if (node instanceof Button) {
                            if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                                System.out.println("Node: " + node + " at " + GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node));
                                if (isPlaced(node) == false && canPlace(node, shipsize) == true) {
                                    if ((GridPane.getRowIndex(node) + shipsize) > mapY) {
                                        save = false;
                                    } else {
                                        node.setId("set");
                                        node.setStyle("-fx-background-color: green");
                                        node.setDisable(true);
                                        for (int size = 1; size <= shipsize - 1; size++) {
                                            getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + size, userGrid).setStyle("-fx-background-color: green");
                                            getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + size, userGrid).setDisable(true);
                                            getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + size, userGrid).setId("set");
                                        }
                                        //save = true;

                                    }
                                }
                            }
                        }
                    }
                } else if (isVertical == false && button == MouseButton.PRIMARY){
                    for (Node node : userGrid.getChildren()) {
                        if (node instanceof Button) {
                            if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                                System.out.println("Node: " + node + " at " + GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node));
                                if (isPlaced(node) == false && canPlace(node, shipsize) == true) {
                                    if ((GridPane.getColumnIndex(node) + shipsize) > mapY) {
                                        save = false;
                                    } else {
                                        node.setId("set");
                                        node.setStyle("-fx-background-color: green");
                                        node.setDisable(true);
                                        for (int size = 1; size <= shipsize - 1; size++) {
                                            getNodeByRowColumnIndex(GridPane.getRowIndex(node) + size, GridPane.getColumnIndex(node), userGrid).setStyle("-fx-background-color: green");
                                            getNodeByRowColumnIndex(GridPane.getRowIndex(node) + size, GridPane.getColumnIndex(node), userGrid).setDisable(true);
                                            getNodeByRowColumnIndex(GridPane.getRowIndex(node) + size, GridPane.getColumnIndex(node), userGrid).setId("set");
                                        }
                                       // save = true;

                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        userGrid.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if (button == MouseButton.SECONDARY) {
                    if (isVertical == true) {
                        isVertical = false;
                    } else if (isVertical == false){
                        isVertical = true;
                    }
                }
            }
        });


    }


        public boolean canPlace(Node node, int shipsize)
        {
            //shipsize += 1;
            int increase;
            Node previousField;

            Node lastShipField = getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) + shipsize, userGrid);

            if((GridPane.getColumnIndex(node) - 1) <= 0) {
                previousField = getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), userGrid);
            } else {
                previousField = getNodeByRowColumnIndex(GridPane.getRowIndex(node), GridPane.getColumnIndex(node) - 1, userGrid);
            }

            if (lastShipField.getId() != "set") {

                if (GridPane.getColumnIndex(node) >= 0){

                    if (previousField.getId() != "set") {
                        return true;
                    } else return false;
                }
                return false;

            }
            else return false;
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
