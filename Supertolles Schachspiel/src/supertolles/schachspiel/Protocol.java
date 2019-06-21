/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supertolles.schachspiel;

/**
 *
 * @author natanael.hoza
 */
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author natanael.hoza
 */
public class Protocol {
    
    JTextArea textArea;
    JScrollPane scrollPane;
    int moveNumber;
    GameLogic logic;
    GameOverlay overlay;
    
    public Protocol (GameOverlay game){
        
        textArea = new JTextArea("Ein neues Schachspiel hat begonnen.", 1, 1);
        textArea.setEnabled(false);
        textArea.setBounds(640, 0, 360, 1000);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        moveNumber = 1;
        overlay = game;
        logic = overlay.getLogic();
    }
    
    public void updateProtocol(Piece movedPiece, boolean pieceCaptured, boolean rochade){
        
        
        if("white".equals(movedPiece.getColour())){
         textArea.append(moveNumber + ".Zug  ");
        } else {
            moveNumber++;
            textArea.append("\n");
        }
        
        //Rochade-Notation
        if(movedPiece.getType() == "KING"){
                if (rochade == true && (movedPiece.getX()== 2)){
                    textArea.append("O-O-O");
                }
                if (rochade == true && (movedPiece.getX()== 6)){
                    textArea.append("O-O");
        }} else {
      
        switch(movedPiece.getType()){
            
            case "KING":
                textArea.append("K");
                break;
            case "QUEEN":
                textArea.append("Q");  
                break;
            case "ROOK":
                textArea.append("R");
                break;
            case "BISHOP":
               textArea.append("B");
                break;
            case "KNIGHT":
               textArea.append("N");
                break;
            case "PAWN":
               textArea.append(" ");
                break;
        }
         if(pieceCaptured == true){
             textArea.append("x");
         }
         
        String[] k = {"a", "b", "c", "d", "e", "f", "g", "h"};
        textArea.append( k[movedPiece.getX()] + Integer.toString(movedPiece.getY()+1));
        
        //TODO ein + wird herausgegeben wenn der gegnerische König nach dem Zug bedroht wurde
        for (int i=0; i<logic.boardlength; i++){
           for (int j=0; j<logic.boardlength; j++){
               if(logic.board[i][j].getType()== "KING" && logic.board[i][j].getColour() != movedPiece.getColour()){
                   if (logic.isCheck(i,j)== true){
                       textArea.append("+");
                   } else {
                       textArea.append("   ");
                       break;
                   }
               }
           } 
        }
        }
            
    }
    
}
