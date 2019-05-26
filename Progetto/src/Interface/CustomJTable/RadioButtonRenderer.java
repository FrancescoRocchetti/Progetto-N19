package Interface.CustomJTable;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class RadioButtonRenderer implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value==null) return null;
        return (Component)value;
    }
}