package club.towr5291.towr5291relicrecoveryscouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class TeleOpActivity extends AppCompatActivity {

    String[] old_data_array0;

    String match_number;
    String team_number;

    int teleopGlyphs = 0;
    int teleopRows = 0;
    int teleopColumns = 0;

    String[] zones = {"0", "1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        old_data_array0 = getIntent().getStringArrayExtra("array");
        match_number = getIntent().getStringExtra("match_number");
        team_number = intent.getStringExtra("team_number");
//        String spinner_position = intent.getStringExtra(PreMatchActivity.spinner_position);

        // Capture the layout's TextView and set the string as its text
        TextView textView_match_number = (TextView) findViewById(R.id.textView_match_number);
        textView_match_number.setText("Match: " + match_number);
//        match_number = Integer.valueOf(data_array[0]);

        TextView textView_team_number = (TextView) findViewById(R.id.textView_team_number);
        textView_team_number.setText("Team: " + team_number);
//        team_number = Integer.valueOf(data_array[1]);

        TextView teleop_glyphs_display = (TextView) findViewById(R.id.teleop_glyphs_display);
        TextView teleop_rows_display = (TextView) findViewById(R.id.teleop_rows_display);
        TextView teleop_columns_display = (TextView) findViewById(R.id.teleop_columns_display);

        teleop_glyphs_display.setText("0");
        teleop_columns_display.setText("0");
        teleop_rows_display.setText("0");

        Button teleop_glyphs_down = (Button) findViewById(R.id.teleop_glyphs_down);
        Button teleop_rows_down = (Button) findViewById(R.id.teleop_rows_down);
        Button teleop_columns_down = (Button) findViewById(R.id.teleop_columns_down);

        teleop_glyphs_down.setEnabled(false);
        teleop_rows_down.setEnabled(false);
        teleop_columns_down.setEnabled(false);
    }

//    Button teleop_glyphs_up = (Button) findViewById(R.id.teleop_glyphs_up);
//    Button teleop_glyphs_down = (Button) findViewById(R.id.teleop_glyphs_down);
//    TextView teleop_glyphs_display = (TextView) findViewById(R.id.teleop_glyphs_display);
//
//    Button teleop_rows_up = (Button) findViewById(R.id.teleop_rows_up);
//    Button teleop_rows_down = (Button) findViewById(R.id.teleop_rows_down);
//    TextView teleop_rows_display = (TextView) findViewById(R.id.teleop_rows_display);
//
//    Button teleop_columns_up = (Button) findViewById(R.id.teleop_columns_up);
//    Button teleop_columns_down = (Button) findViewById(R.id.teleop_columns_down);
//    TextView teleop_columns_display = (TextView) findViewById(R.id.teleop_columns_display);
//
//    CheckBox teleop_cipher1 = (CheckBox) findViewById(R.id.teleop_cipher1);
//    CheckBox teleop_cipher2 = (CheckBox) findViewById(R.id.teleop_cipher2);
//
//    CheckBox teleop_relic1_standing = (CheckBox) findViewById(R.id.teleop_relic1_standing);
//    CheckBox teleop_relic2_standing = (CheckBox) findViewById(R.id.teleop_relic2_standing);
//
//    CheckBox teleop_balanced = (CheckBox) findViewById(R.id.teleop_balanced);



    public void onTeleopGlyphsUpClicked(View view) {
        TextView teleop_glyphs_display = (TextView) findViewById(R.id.teleop_glyphs_display);
        teleopGlyphs++;
        teleop_glyphs_display.setText(String.valueOf(teleopGlyphs));

        Button teleop_glyphs_down = (Button) findViewById(R.id.teleop_glyphs_down);
        if (teleopGlyphs != 0) {
            teleop_glyphs_down.setEnabled(true);
        }
    }

    public void onTeleopGlyphsDownClicked(View view) {
        TextView teleop_glyphs_display = (TextView) findViewById(R.id.teleop_glyphs_display);
        teleopGlyphs--;
        teleop_glyphs_display.setText(String.valueOf(teleopGlyphs));

        Button teleop_glyphs_down = (Button) findViewById(R.id.teleop_glyphs_down);
        if (teleopGlyphs == 0) {
            teleop_glyphs_down.setEnabled(false);
        }
    }

    public void onTeleopRowsUpClicked(View view) {
        TextView teleop_rows_display = (TextView) findViewById(R.id.teleop_rows_display);
        teleopRows++;
        teleop_rows_display.setText(String.valueOf(teleopRows));

        Button teleop_rows_down = (Button) findViewById(R.id.teleop_rows_down);
        if (teleopRows != 0) {
            teleop_rows_down.setEnabled(true);
        }

        Button teleop_rows_up = (Button) findViewById(R.id.teleop_rows_up);
        if (teleopRows == 8) {
            teleop_rows_up.setEnabled(false);
        }
    }

    public void onTeleopRowsDownClicked(View view) {
        TextView teleop_rows_display = (TextView) findViewById(R.id.teleop_rows_display);
        teleopRows--;
        teleop_rows_display.setText(String.valueOf(teleopRows));

        Button teleop_rows_down = (Button) findViewById(R.id.teleop_rows_down);
        if (teleopRows == 0) {
            teleop_rows_down.setEnabled(false);
        }

        Button teleop_rows_up = (Button) findViewById(R.id.teleop_rows_up);
        if (teleopRows != 8) {
            teleop_rows_up.setEnabled(true);
        }
    }

    public void onTeleopColumnsUpClicked(View view) {
        TextView teleop_columns_display = (TextView) findViewById(R.id.teleop_columns_display);
        teleopColumns++;
        teleop_columns_display.setText(String.valueOf(teleopColumns));

        Button teleop_columns_down = (Button) findViewById(R.id.teleop_columns_down);
        if (teleopColumns != 0) {
            teleop_columns_down.setEnabled(true);
        }

        Button teleop_columns_up = (Button) findViewById(R.id.teleop_columns_up);
        if (teleopColumns == 6) {
            teleop_columns_up.setEnabled(false);
        }
    }

    public void onTeleopColumnsDownClicked(View view){
        TextView teleop_columns_display = (TextView) findViewById(R.id.teleop_columns_display);
        teleopColumns--;
        teleop_columns_display.setText(String.valueOf(teleopColumns));

        Button teleop_columns_down = (Button) findViewById(R.id.teleop_columns_down);
        if (teleopColumns == 0) {
            teleop_columns_down.setEnabled(false);
        }

        Button teleop_columns_up = (Button) findViewById(R.id.teleop_columns_up);
        if (teleopColumns != 6) {
            teleop_columns_up.setEnabled(true);
        }
    }

    public void gotoComments(View view) {
        Spinner teleop_relic1_zone = (Spinner)findViewById(R.id.teleop_relic1_zone);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, zones);
        int spinner_position = teleop_relic1_zone.getSelectedItemPosition();
        String relic1_zone = zones[Integer.valueOf(spinner_position)];

        Spinner teleop_relic2_zone = (Spinner)findViewById(R.id.teleop_relic1_zone);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, zones);
        int spinner_position2 = teleop_relic2_zone.getSelectedItemPosition();
        String relic2_zone = zones[Integer.valueOf(spinner_position)];

        CheckBox teleop_cipher1 = (CheckBox) findViewById(R.id.teleop_cipher1);
        CheckBox teleop_cipher2 = (CheckBox) findViewById(R.id.teleop_cipher2);

        CheckBox teleop_relic1_standing = (CheckBox) findViewById(R.id.teleop_relic1_standing);
        CheckBox teleop_relic2_standing = (CheckBox) findViewById(R.id.teleop_relic2_standing);

        CheckBox teleop_balanced = (CheckBox) findViewById(R.id.teleop_balanced);

        int teleop_relic_zone1 = 0;
        int teleop_relic_zone2 = 0;
        int teleop_relic_zone3 = 0;
        int teleop_relic_standing = 0;

        if(spinner_position >= 1) {teleop_relic_zone1++;};
        if(spinner_position2 >= 1) {teleop_relic_zone1++;};
        if(spinner_position >= 2) {teleop_relic_zone2++;};
        if(spinner_position2 >= 2) {teleop_relic_zone2++;};
        if(spinner_position >= 3) {teleop_relic_zone3++;};
        if(spinner_position2 >= 3) {teleop_relic_zone3++;};

        String[] old_data_array1 = push(old_data_array0, String.valueOf(teleopGlyphs));
        String[] old_data_array2 = push(old_data_array1, String.valueOf(teleopRows));
        String[] old_data_array3 = push(old_data_array2, String.valueOf(teleopColumns));
        String[] old_data_array4 = push(old_data_array3, String.valueOf(toInt(teleop_cipher1.isChecked()) + toInt(teleop_cipher2.isChecked())));
        String[] old_data_array5 = push(old_data_array4, String.valueOf(teleop_relic_zone1));
        String[] old_data_array6 = push(old_data_array5, String.valueOf(teleop_relic_zone2));
        String[] old_data_array7 = push(old_data_array6, String.valueOf(teleop_relic_zone3));
        String[] old_data_array8 = push(old_data_array7, String.valueOf(toInt(teleop_relic1_standing.isChecked())+toInt(teleop_relic2_standing.isChecked())));
        String[] old_data_array9 = push(old_data_array8, String.valueOf(toInt(teleop_balanced.isChecked())));
        String[] data_array = push(old_data_array9, "");

        Intent intent = new Intent(this, FinalizeActivity.class);
        intent.putExtra("team_number", team_number);
        intent.putExtra("match_number", match_number);
        intent.putExtra("array", data_array);
        startActivity(intent);

    }

    private static String[] push(String[] array, String push) {
        String[] longer = new String[array.length + 1];
        for (int i = 0; i < array.length; i++)
            longer[i] = array[i];
        longer[array.length] = push;
        return longer;
    }

    private static int toInt(boolean variable) {
        if(variable == true) {
            return 1;
        } else {
            return 0;
        }
    }
}
