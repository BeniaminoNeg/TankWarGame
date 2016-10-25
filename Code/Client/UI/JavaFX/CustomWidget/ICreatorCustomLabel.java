package Client.UI.JavaFX.CustomWidget;

import javafx.scene.paint.Color;

/**
 * Created by beniamino on 25/02/16.
 */
public interface ICreatorCustomLabel {
    //TODO Qui le classi figlie implementano entrambe questo metodo ma alle azioni non interessa assolutamente il parametro vera
    ICustomLabel makeComponent(String idComponent, String idPadre, boolean vera);

    String getIdType();

    void setIdType(String idType);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Color getColor();

    void setTrue();

    void setFalse();
}
