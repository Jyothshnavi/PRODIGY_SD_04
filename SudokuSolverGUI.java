import javax.swing.*;
import java.awt.*;
public class SudokuSolverGUI extends JFrame {
    private static final int SIZE=9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private int[][] board ={
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };    
    public SudokuSolverGUI(){
        setTitle("Sudoku Solver");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel gridPanel=new JPanel(new GridLayout(SIZE,SIZE));
        Font font = new Font("Arial", Font.BOLD, 20);
        for(int row=0;row<SIZE;row++){
            for(int col=0;col<SIZE;col++){
                cells[row][col]=new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(font);
                if (board[row][col]!=0) {
                    cells[row][col].setText(String.valueOf(board[row][col]));
                    cells[row][col].setEditable(false);
                    cells[row][col].setBackground(Color.BLACK);
                }
                gridPanel.add(cells[row][col]);
            }
        }
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e->{
            if (solveSudoku(board)) {
                updateGrid();
            } else {
                JOptionPane.showMessageDialog(this, "No solution exists");
            }
        });
        add(gridPanel, BorderLayout.CENTER);
        add(solveButton, BorderLayout.SOUTH);
    }
    private void updateGrid(){
        for(int row=0;row<SIZE;row++){
            for(int col=0;col<SIZE;col++){
                cells[row][col].setText(String.valueOf(board[row][col]));
            }
        }
    }
    private boolean solveSudoku(int[][]board){
        for(int row=0;row<SIZE;row++){
            for(int col=0;col<SIZE;col++){
                if (board[row][col]==0) {
                    for(int num =1;num<=SIZE;num++){
                        if (isSafe(board,row,col,num)) {
                            board[row][col]=num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col]=0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isSafe(int[][] board,int row,int col,int num) {
        for (int i=0;i<SIZE;i++) {
            if (board[row][i]==num||board[i][col]==num) {
                return false;
            }
        }
        int startRow=row-row%3,startCol=col-col%3;
        for(int r=startRow;r<startRow+3;r++){
            for(int c=startCol;c<startCol+3;c++){
                if (board[r][c]==num) {
                    return false;
                }
            }
        }
        return true;
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(()->{
        SudokuSolverGUI solver=new SudokuSolverGUI();
        solver.setVisible(true);
    });
  }
}