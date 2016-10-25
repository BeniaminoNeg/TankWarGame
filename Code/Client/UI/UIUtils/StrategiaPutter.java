package Client.UI.UIUtils;

import Client.UI.JavaFX.CustomWidget.ICustomLabel;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by emanuele on 03/03/16.
 */
public class StrategiaPutter {

    private VBox strategiaVBox;
    private Integer indentazione = 0;

    public StrategiaPutter(VBox stratVBox) {
        this.strategiaVBox = stratVBox;
    }

    public void addLabel(ICustomLabel iCustomLabel, boolean diretta) {
        if (diretta) {
            indentazione = 0;
        } else {
            indentazione = indentazione + 1;
        }
        HBox elemento = creaSpazi();
        elemento.getChildren().add((Node) iCustomLabel);
        strategiaVBox.getChildren().add(elemento);
    }

    private HBox creaSpazi() {
        HBox elemento = new HBox();
        int i;
        for (i = 0; i < indentazione; i = i + 1) {
            Label space = new Label();
            space.setMinWidth(50);
            elemento.getChildren().add(space);
        }
        return elemento;
    }

    public void removeLastLabel() {
        Integer numRighe = strategiaVBox.getChildren().size();
        if (numRighe != 0) {
            if (numRighe > 1) {
                HBox nuovaUltimaRiga = (HBox) strategiaVBox.getChildren().get(numRighe - 2);
                Integer numeroDiRiempimentiNuovaUltimaRiga = nuovaUltimaRiga.getChildren().size() - 1;//-1 perchè una è la label
                indentazione = numeroDiRiempimentiNuovaUltimaRiga;
            } else {//A questo punto vuol dire che sto togliendo la prima sopra
                indentazione = 0;
            }
            strategiaVBox.getChildren().remove(numRighe - 1);
        }
    }

}
