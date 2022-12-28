package com.example.a120181849;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class third_main extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[7][7];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        textViewPlayer1.setTypeface(null, Typeface.BOLD);
        textViewPlayer2.setTypeface(null, Typeface.BOLD);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(third_main.this);
                ad.setIcon(R.mipmap.ic_launcher);
                ad.setTitle("경고");
                ad.setMessage("리셋하겠습니까?");
                ad.setNegativeButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                resetGame();
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 49) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }
    private boolean checkForWin() {
        String[][] field = new String[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 7; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && field[i][0].equals(field[i][3])
                    && !field[i][0].equals("")) {
                return true;
            } if (field[i][1].equals(field[i][2])
                    && field[i][1].equals(field[i][3])
                    && field[i][1].equals(field[i][4])
                    && !field[i][1].equals("")) {
                return true;
            }
            if (field[i][2].equals(field[i][3])
                    && field[i][2].equals(field[i][4])
                    && field[i][2].equals(field[i][5])
                    && !field[i][2].equals("")) {
                return true;
            }
            if (field[i][3].equals(field[i][4])
                    && field[i][3].equals(field[i][5])
                    && field[i][3].equals(field[i][6])
                    && !field[i][3].equals("")) {
                return true;
            }
        }


        for (int i = 0; i < 7; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i].equals(field[3][i])
                    && !field[0][i].equals("")) {
                return true;
            } if (field[1][i].equals(field[3][i])
                    && field[1][i].equals(field[4][i])
                    && field[0][i].equals(field[5][i])
                    && !field[1][i].equals("")) {
                return true;
            }if (field[2][i].equals(field[3][i])
                    && field[2][i].equals(field[4][i])
                    && field[2][i].equals(field[5][i])
                    && !field[2][i].equals("")) {
                return true;
            }
            if (field[3][i].equals(field[4][i])
                    && field[3][i].equals(field[5][i])
                    && field[3][i].equals(field[6][i])
                    && !field[3][i].equals("")) {
                return true;
            }
        }


        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0].equals(field[3][3])
                && !field[0][0].equals("")) {
            return true;
        }    if (field[1][1].equals(field[2][2])
                && field[1][1].equals(field[3][3])
                && field[1][1].equals(field[4][4])
                && !field[1][1].equals("")) {
            return true;
        }    if (field[2][2].equals(field[3][3])
                && field[2][2].equals(field[4][4])
                && field[2][2].equals(field[5][5])
                && !field[2][2].equals("")) {
            return true;
        }
        if (field[3][3].equals(field[4][4])
                && field[3][3].equals(field[5][5])
                && field[3][3].equals(field[6][6])
                && !field[3][3].equals("")) {
            return true;
        } if (field[0][1].equals(field[1][2])
                && field[0][1].equals(field[2][3])
                && field[0][1].equals(field[3][4])
                && !field[0][1].equals("")) {
            return true;
        }
        if (field[1][2].equals(field[2][3])
                && field[1][2].equals(field[3][4])
                && field[1][2].equals(field[4][5])
                && !field[1][2].equals("")) {
            return true;
        }
        if (field[2][3].equals(field[3][4])
                && field[2][3].equals(field[4][5])
                && field[2][3].equals(field[5][6])
                && !field[2][3].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][3])
                && field[0][2].equals(field[2][4])
                && field[0][2].equals(field[3][5])
                && !field[0][2].equals("")) {
            return true;
        }
        if (field[1][3].equals(field[2][4])
                && field[1][3].equals(field[3][5])
                && field[1][3].equals(field[4][6])
                && !field[1][3].equals("")) {
            return true;
        }
        if (field[0][3].equals(field[1][4])
                && field[0][3].equals(field[2][5])
                && field[0][3].equals(field[3][6])
                && !field[0][3].equals("")) {
            return true;
        }
        if (field[1][0].equals(field[2][1])
                && field[1][0].equals(field[3][2])
                && field[1][0].equals(field[4][3])
                && !field[1][0].equals("")) {
            return true;
        }
        if (field[2][1].equals(field[3][2])
                && field[2][1].equals(field[4][3])
                && field[2][1].equals(field[5][4])
                && !field[2][1].equals("")) {
            return true;
        } if (field[3][2].equals(field[4][3])
                && field[3][2].equals(field[5][4])
                && field[3][2].equals(field[6][5])
                && !field[3][2].equals("")) {
            return true;
        } if (field[2][0].equals(field[3][1])
                && field[2][0].equals(field[4][2])
                && field[2][0].equals(field[5][3])
                && !field[2][0].equals("")) {
            return true;
        }
        if (field[3][1].equals(field[4][2])
                && field[3][1].equals(field[5][3])
                && field[3][1].equals(field[6][4])
                && !field[3][1].equals("")) {
            return true;
        } if (field[3][0].equals(field[4][1])
                && field[3][0].equals(field[5][2])
                && field[3][0].equals(field[6][3])
                && !field[3][0].equals("")) {
            return true;
        }

        if (field[0][6].equals(field[1][5])
                && field[0][6].equals(field[2][4])
                && field[0][6].equals(field[3][3])
                && !field[0][6].equals("")) {
            return true;
        } if (field[1][5].equals(field[2][4])
                && field[1][5].equals(field[3][3])
                && field[1][5].equals(field[4][2])
                && !field[1][5].equals("")) {
            return true;
        }if (field[2][4].equals(field[3][3])
                && field[2][4].equals(field[4][2])
                && field[2][4].equals(field[5][1])
                && !field[2][4].equals("")) {
            return true;
        }
        if (field[3][3].equals(field[4][2])
                && field[3][3].equals(field[5][1])
                && field[3][3].equals(field[6][0])
                && !field[3][3].equals("")) {
            return true;
        }if (field[1][6].equals(field[2][5])
                && field[1][6].equals(field[3][4])
                && field[1][6].equals(field[4][3])
                && !field[1][6].equals("")) {
            return true;
        }if (field[2][5].equals(field[3][4])
                && field[2][5].equals(field[4][3])
                && field[2][5].equals(field[5][2])
                && !field[2][5].equals("")) {
            return true;
        }if (field[3][4].equals(field[4][3])
                && field[3][4].equals(field[5][2])
                && field[3][4].equals(field[6][1])
                && !field[3][4].equals("")) {
            return true;
        }if (field[2][6].equals(field[3][5])
                && field[2][6].equals(field[4][4])
                && field[2][6].equals(field[5][3])
                && !field[2][6].equals("")) {
            return true;
        }if (field[3][5].equals(field[4][4])
                && field[3][5].equals(field[5][3])
                && field[3][5].equals(field[6][2])
                && !field[3][5].equals("")) {
            return true;
        }if (field[3][6].equals(field[4][5])
                && field[3][6].equals(field[5][4])
                && field[3][6].equals(field[6][3])
                && !field[3][6].equals("")) {
            return true;
        }if (field[0][5].equals(field[1][4])
                && field[0][5].equals(field[2][3])
                && field[0][5].equals(field[3][2])
                && !field[0][5].equals("")) {
            return true;
        }if (field[1][4].equals(field[2][3])
                && field[1][4].equals(field[3][2])
                && field[1][4].equals(field[4][1])
                && !field[1][4].equals("")) {
            return true;
        }if (field[2][3].equals(field[3][2])
                && field[2][3].equals(field[4][1])
                && field[2][3].equals(field[5][0])
                && !field[2][3].equals("")) {
            return true;
        }if (field[0][4].equals(field[1][3])
                && field[0][4].equals(field[2][2])
                && field[0][4].equals(field[3][1])
                && !field[0][4].equals("")) {
            return true;
        }if (field[1][3].equals(field[2][2])
                && field[1][3].equals(field[3][1])
                && field[1][3].equals(field[4][0])
                && !field[1][3].equals("")) {
            return true;
        }if (field[0][3].equals(field[1][2])
                && field[0][3].equals(field[2][1])
                && field[0][3].equals(field[3][0])
                && !field[0][3].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "플레이어1 승!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "플레이어2 승!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "무승부!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("player1:" + player1Points);
        textViewPlayer2.setText("player2:" + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
