package Shared.Domain;

import Shared.Domain.Creator.ICreator;

import java.util.Map;

/**
 * Created by beniamino on 25/02/16.
 */
public interface ICatalogo {
    ICreator getCreator(String idType);

    Map<String, ICreator> getCreators();

    void setCreators(Map<String, ICreator> creators);

    Map getMap();

}
