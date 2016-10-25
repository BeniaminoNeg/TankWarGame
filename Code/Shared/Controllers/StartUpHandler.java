package Shared.Controllers;

import Shared.Domain.CatalogoAzioneCreator;
import Shared.Domain.CatalogoCondizioneCreator;
import Shared.Domain.ICatalogo;
import Shared.Domain.Player;

import java.util.Map;

/**
 * Created by beniamino on 24/02/16.
 */
public class StartUpHandler {
    private static StartUpHandler singletonInstance = null;
    private Player player;
    private ICatalogo catalogoCondCreator;
    private ICatalogo catalogoAzCreator;

    private StartUpHandler() {
        this.catalogoCondCreator = new CatalogoCondizioneCreator();
        this.catalogoAzCreator = new CatalogoAzioneCreator();
    }

    public static StartUpHandler getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new StartUpHandler();
        }
        return singletonInstance;
    }



    public void setPlayer(Player player) {
        this.player = player;
    }

    public ICatalogo getCatalogoCondCreator() {
        return catalogoCondCreator;
    }

    public void setCatalogoCondCreator(ICatalogo catalogoCondCreator) {
        this.catalogoCondCreator = catalogoCondCreator;
    }

    public Map getCatalogoConditionCreatorMap() {
        return this.catalogoCondCreator.getMap();
    }

    public ICatalogo getCatalogoAzCreator() {
        return catalogoAzCreator;
    }

    public void setCatalogoAzCreator(ICatalogo catalogoAzCreator) {
        this.catalogoAzCreator = catalogoAzCreator;
    }

    public Map getCatalogoAzCreatorMap() {
        return this.catalogoAzCreator.getMap();
    }
}
