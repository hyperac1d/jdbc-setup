//Open new frame
FrmName create = new FrmCreate();
create.setVisible(true);


//Set the frame at the middle
public void setCenterFrame(){
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
}

//Change icon
public void changeIcon(){
    ImageIcon icon = new ImageIcon("src/Activity1/chibi.png");
    this.setIconImage(icon.getImage());
}

//stretching image
Toolkit toolkit = Toolkit.getDefaultToolkit();
Image image = icon.getImage();
Image scaledImage = image.getScaledInstance(lblIMAGE.getWidth(),lblIMAGE.getHeight(), Image.SCALE_DEFAULT);                                                                
lblIMAGE.setIcon(new javax.swing.ImageIcon(scaledImage));



//Strecth image to a jLabel
public Image stretchImage(String path,JLabel label)
{
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = toolkit.getImage(path);
    Image scaledImage = image.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT);
    return scaledImage;
}


public void refreshTable(){
    //Load data
    if(SQLite.openDB()){
        String[] columns = {"ID", "NAME", "BIRTHDAY", "IMAGE"};
        String[][] records = SQLite.read("profile",columns);
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(records, columns);
        this.tblProfile.setModel(model);
        
        //Additional COde Snippets for JTABLE behavior
        ListSelectionListener lsl = new ListSelectionListener(){
            public void valueChanged(javax.swing.event.ListSelectionEvent event) {
                if (tblProfile.getSelectedRow() > -1) {
                    int row = tblProfile.getSelectedRow();
                    //JOptionPane.showMessageDialog(null, "Row is " + row);
                    if(SQLite.openDB()){
                        String sr_id = tblProfile.getValueAt(tblProfile.getSelectedRow(), 0).toString();
                        int id = Integer.parseInt(sr_id);
                        String[][] result = SQLite.read("profile", "ID=" + id);
                        
                        txtId.setText(result[0][0]);
                        txtName.setText(result[0][1]);
                        String date = result[0][2];
                        try {
                            java.util.Date date2 = new SimpleDateFormat("MMM dd, yyy").parse(date);
                            dcBirthdayChooser.setDate(date2);
                        } catch (ParseException ex) {
                            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                        try{
                            byte[] in = SQLite.read("profile", "image", id);
                            ImageIcon icon =new ImageIcon(in);

                            //stretching image
                            Toolkit toolkit = Toolkit.getDefaultToolkit();
                            Image image = icon.getImage();
                            Image scaledImage = image.getScaledInstance(lblIMAGE.getWidth(),lblIMAGE.getHeight(), Image.SCALE_DEFAULT);                                                                
                            lblIMAGE.setIcon(new javax.swing.ImageIcon(scaledImage));  
                        }
                        catch(Exception e){
                            System.out.println("Error: " + e.getMessage());
                        }
                        
                        SQLite.closeDB();
                    }
                }
            }
        };
        this.tblProfile.getSelectionModel().addListSelectionListener(lsl);
        
        SQLite.closeDB();
    }        
}



//Update Table - Search Key Released
try{
    conn = getConnection(SQLite.url);
    stmt = conn.createStatement();
    String result = txtSearch.getText();
    if(txtSearch.equals("")){
        showTable();
    }else{
        String query = "SELECT * FROM Guestbook WHERE NAME LIKE'"+result+"%'"+"OR ID LIKE'"+result+"%'"+"OR CONTACTNO LIKE'"+result+"%'"+"OR EMAIL LIKE'"+result+"%'"+"OR GENDER LIKE'"+result+"%'" ;
//                String query = "SELECT * FROM Guestbook WHERE NAME LIKE '"+result+"%'";
        ResultSet rs = stmt.executeQuery(query);
        this.tblGuestbook.setModel(DbUtils.resultSetToTableModel(rs)); 
	}       
}catch(Exception e){
   System.out.println("Error: "+e);
}