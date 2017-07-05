package monitoring.calendar;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
   

public class TextAreaRenderer extends JScrollPane implements TableCellRenderer
{
   JTextArea textarea;
  
   public TextAreaRenderer() {
      textarea = new JTextArea();
      textarea.setLineWrap(true);
      textarea.setWrapStyleWord(true);
      getViewport().add(textarea);
   }
  
   public Component getTableCellRendererComponent(JTable table, Object value,
                                  boolean isSelected, boolean hasFocus,
                                  int row, int column)
   {
      if (isSelected) {
         setForeground(table.getSelectionForeground());
         setBackground(table.getSelectionBackground());
         textarea.setForeground(table.getSelectionForeground());
         textarea.setBackground(table.getSelectionBackground());
      } else {
         setForeground(table.getForeground());
         setBackground(table.getBackground());
         textarea.setForeground(table.getForeground());
         textarea.setBackground(table.getBackground());
      }
  
      textarea.setText((String) value);
      textarea.setCaretPosition(0);
      return this;
   }
}
  
