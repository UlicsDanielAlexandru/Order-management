package businessLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Clasa TablePopulator este utilizata pentru a completa atat header-ul, cat si
 * continutul unui tabel, folosind reflection.
 */

public class TablePopulator {

    /**
     * Metoda va genera header-ul de tabel folosind campurile obiectelor trimise,
     * urmand ca pentru fiecare obiecte sa preia valoarea acelor campuri si sa le
     * introduca in tabel.
     * @param table tabelul care se doreste a fi completat
     * @param objects lista de obiecte care vor completa tabelul
     */

    public static void populateTable(JTable table, List<Object> objects)
    {
        int index=0;
        DefaultTableModel defaultTableModel= (DefaultTableModel) table.getModel();
        for(Field field: objects.get(0).getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            defaultTableModel.addColumn(field.getName());
            index++;
        }
        for(int i=0;i<index;i++)
        {
            table.getColumnModel().getColumn(i).setPreferredWidth(190);
        }
        for(Object object: objects)
        {
            index=0;
            Object[] data=new Object[20];
            for(Field field: object.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                try {
                    data[index++]=field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            defaultTableModel.addRow(data);
        }
    }
}
