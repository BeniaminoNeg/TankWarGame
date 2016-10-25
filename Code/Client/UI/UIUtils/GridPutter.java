package Client.UI.UIUtils;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Created by beniamino on 25/02/16.
 */
public class GridPutter {
    private GridPane grid;
    private Integer maxXGrid;
    private Integer maxYGrid;
    private Integer currentX;
    private Integer currentY;

    public GridPutter(GridPane grid) {
        this.grid = grid;
        this.maxXGrid = grid.getColumnConstraints().size();
        this.maxYGrid = grid.getRowConstraints().size();
        this.currentX = 0;
        this.currentY = 0;
    }

    public void put(Node node) {

        grid.add(node, currentY, currentX);
        if (currentX + 2 >= maxXGrid) {
            if (currentY + 2 < maxYGrid) {
                currentY = currentY + 2;
                currentX = 0;
            }
        } else {
            currentX = currentX + 2;
        }
    }

    public boolean isFull() {
        boolean full = false;
        if (currentX + 2 >= maxXGrid) {
            if (currentY + 2 >= maxYGrid) {
                full = true;
            }
        }
        return full;
    }
}
