// Adding list to a combo box and getSelected item

ComboBox cb = new ComboBox();
db.addItem("1st Year");
db.addItem("2nd Year");
db.addItem("3rd Year");
db.addItem("4th Year")
db.addItem("5th Year");
String choice = cb.getSelectedItem();
String selected_text = cb.getItemAt(cb.getSelectedIndex());

