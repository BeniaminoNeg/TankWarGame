package Client.UI.JavaFX.CustomWidget;

import javafx.scene.control.Label;

/**
 * Created by emanuele on 01/03/16.
 */
public class ActionLabel extends Label implements ICustomLabel {

    private String name;
    private String description;
    private String idAz;
    private String idPadre;

    public ActionLabel(String name, String description, String idAz, String idPadre) {
        this.name = name;
        this.description = description;
        this.idAz = idAz;
        this.idPadre = idPadre;
    }


    @Override
    public String getIdComponent() {
        return this.idAz;
    }

    @Override
    public void setIdComponent(String idComponent) {
        this.idAz = idComponent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getIdPadre() {
        return this.idPadre;
    }

    @Override
    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }
}
