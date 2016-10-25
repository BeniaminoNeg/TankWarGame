package Client.UI.JavaFX.CustomWidget;

import javafx.scene.control.Label;

/**
 * Created by emanuele on 01/03/16.
 */
public class ConditionLabel extends Label implements ICustomLabel {

    private String name;
    private String description;
    private String idCond;
    private boolean vera;
    private String idPadre;

    public ConditionLabel(String name, String description, String idCond, String idPadre, boolean vera) {
        this.vera = vera;
        if (!this.vera) {
            this.name = "Se NON " + name;
        } else {
            this.name = "Se " + name;
        }
        this.description = description;
        this.idCond = idCond;
        this.idPadre = idPadre;
    }


    @Override
    public String getIdComponent() {
        return this.idCond;
    }

    @Override
    public void setIdComponent(String idCond) {
        this.idCond = idCond;
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

    public boolean getVera() {
        return this.vera;
    }

    public void setVera(boolean vera) {
        this.vera = vera;
    }


}
