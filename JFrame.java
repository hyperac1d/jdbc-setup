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