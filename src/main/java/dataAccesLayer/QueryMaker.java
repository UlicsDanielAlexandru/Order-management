package dataAccesLayer;

import java.lang.reflect.Field;

/**
 * Clasa QueryMaker este utilizata pentru a crea string-urile necesare folosite in
 * query-uri.
 */

public class QueryMaker {

    /**
     * Metoda este utilizate pentru a crea un string pentru un query de inserare
     * @param type clasa obiectului care se doreste a fi inserat
     * @return string-ul pentru query-ul de inserare
     */

    public static String createInsertQuery(Class type)
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append("(");
        StringBuilder fields=new StringBuilder();
        Field[] fieldsList=type.getDeclaredFields();
        for(int i=1;i<fieldsList.length;i++)
        {
            fields.append(fieldsList[i].getName());
            if((i+1)!=fieldsList.length)
                fields.append(",");
        }
        stringBuilder.append(String.valueOf(fields));
        stringBuilder.append(") VALUES (");
        fields=new StringBuilder();
        for(int i=1;i< fieldsList.length;i++)
        {
            fields.append("?");
            if((i+1)!=fieldsList.length)
                fields.append(",");
        }
        stringBuilder.append(fields);
        stringBuilder.append(")");
        return String.valueOf(stringBuilder);
    }

    /**
     * Metoda utilizata pentru a crea un query pentru actualizare.
     * @param type clasa obiectului care se doreste a fi actualizat
     * @param updateField campul care se doreste a fi actualizat
     * @param filterField campul care va realiza filtrarea, pentru a actualiza o
     *                    singura instanta
     * @return string-ul pentru query-ul de actualizare
     */

    public static String createUpdateQuery(Class type,String updateField,String filterField)
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" SET ");
        stringBuilder.append(updateField);
        stringBuilder.append("=? ");
        stringBuilder.append("WHERE ");
        stringBuilder.append(filterField);
        stringBuilder.append("=?");
        return String.valueOf(stringBuilder);
    }

    /**
     * Metoda utilizata pentru a crea string-ul pentru un query de stergere
     * @param type clasa obiectului care se doreste a fi sters
     * @param field campul care va realiza filtrarea, astfel incat o singura instanta
     *              sa fie stearsa
     * @return string-ul pentru query-ul de stergere
     */

    public static String createDeleteQuery(Class type,String field)
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("DELETE FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE ");
        stringBuilder.append(field);
        stringBuilder.append("=?");
        return String.valueOf(stringBuilder);
    }

    /**
     * Metoda utilizata pentru a crea string-ul pentru un query de selectare
     * @param type clasa obiectului cautat
     * @param field campul care va filtra rezultatele
     * @return string-ul pentru query-ul de selectare
     */

    public static String createSelectQuery(Class type,String field)
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE ");
        stringBuilder.append(field);
        stringBuilder.append("=?");
        return String.valueOf(stringBuilder);
    }
}
