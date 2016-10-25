package Shared.Domain.Creator;

import java.util.Map;

/**
 * Created by beniamino on 25/02/16.
 */
public interface ICreator {
    String getIdType();

    void setIdType(String idType);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Map getMap();

}
