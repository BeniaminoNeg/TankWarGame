package Client.UI.JavaFX.CustomWidget;

import Client.UI.UIUtils.LabelsMaker;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * Created by beniamino on 25/02/16.
 */
public class ActionCreatorLabel extends Label implements ICreatorCustomLabel {

    private String idType;
    private String name;
    private String description;

    public ActionCreatorLabel(String idTypeAz, String name, String description) {
        this.idType = idTypeAz;
        this.name = name;
        this.description = description;
    }

    public ActionCreatorLabel(String text, String idTypeAz, String name, String description) {
        super(text);
        this.idType = idTypeAz;
        this.name = name;
        this.description = description;
    }

    public ActionCreatorLabel(String text, Node graphic, String idTypeAz, String name, String description) {
        super(text, graphic);
        this.idType = idTypeAz;
        this.name = name;
        this.description = description;
    }

    @Override
    public ICustomLabel makeComponent(String idAz, String idPadre, boolean vera) {
        ActionLabel actionLabel = new ActionLabel(name, description, idAz, idPadre);
        actionLabel.setPadding(new Insets(0, 0, 0, 20));
        LabelsMaker.layoutizeLabel(actionLabel, (Color) this.getBackground().getFills().get(0).getFill());
        return actionLabel;

    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idTypeAz) {
        this.idType = idTypeAz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return (Color) this.getBackground().getFills().get(0).getFill();
    }

    @Override
    public void setTrue() {

    }

    @Override
    public void setFalse() {

    }
}
