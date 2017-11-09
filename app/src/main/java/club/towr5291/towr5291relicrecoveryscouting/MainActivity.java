package club.towr5291.towr5291relicrecoveryscouting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	int autonomousGlyphs = 0;

	int teleopGlyphs = 0;
	int teleopRows = 0;
	int teleopColumns = 0;

	// The Declaration of Things

	EditText prematch_input_scout_name;
	Spinner prematch_select_scout_team;

	Spinner prematch_select_team;
	EditText prematch_input_match;

	CheckBox autonomous_balanced_checkbox;

	Button autonomous_glyphs_up;
	Button autonomous_glyphs_down;
	TextView autonomous_glyphs_display;
	CheckBox autonomous_key_bonus;

	CheckBox autonomous_own_jewel;
	CheckBox autonomous_other_jewel;

	CheckBox autonomous_safe_zone;

	Button teleop_glyphs_up = (Button) findViewById(R.id.teleop_glyphs_up);
	Button teleop_glyphs_down = (Button) findViewById(R.id.teleop_glyphs_down);
	TextView teleop_glyphs_display = (TextView) findViewById(R.id.teleop_glyphs_display);

	Button teleop_rows_up;
	Button teleop_rows_down;
	TextView teleop_rows_display;

	Button teleop_columns_up;
	Button teleop_columns_down;
	TextView teleop_columns_display;

	CheckBox teleop_cipher1;
	CheckBox teleop_cipher2;

	Spinner teleop_relic1_select_zone;
	Spinner teleop_relic2_select_zone;

	CheckBox teleop_relic1_standing;
	CheckBox teleop_relic2_standing;

	CheckBox teleop_balanced;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		prematch_input_scout_name = (EditText) findViewById(R.id.prematch_input_scout_name);
		prematch_select_scout_team = (Spinner) findViewById(R.id.prematch_select_scout_team);

		prematch_select_team = (Spinner) findViewById(R.id.prematch_select_team);
		prematch_input_match = (EditText) findViewById(R.id.prematch_input_match);

		autonomous_balanced_checkbox = (CheckBox) findViewById(R.id.autonomous_robot_balanced);

		autonomous_glyphs_up = (Button) findViewById(R.id.autonomous_glyphs_up);
		autonomous_glyphs_down = (Button) findViewById(R.id.autonomous_glyphs_down);
		autonomous_glyphs_display = (TextView) findViewById(R.id.autonomous_glyphs_display);
		autonomous_key_bonus = (CheckBox) findViewById(R.id.autonomous_key_bonus);

		autonomous_own_jewel = (CheckBox) findViewById(R.id.autonomous_own_jewel);
		autonomous_other_jewel = (CheckBox) findViewById(R.id.autonomous_other_jewel);

		autonomous_safe_zone = (CheckBox) findViewById(R.id.autonomous_safe_zone);

		teleop_glyphs_up = (Button) findViewById(R.id.teleop_glyphs_up);
		teleop_glyphs_down = (Button) findViewById(R.id.teleop_glyphs_down);
		teleop_glyphs_display = (TextView) findViewById(R.id.teleop_glyphs_display);

		teleop_rows_up = (Button) findViewById(R.id.teleop_rows_up);
		teleop_rows_down = (Button) findViewById(R.id.teleop_rows_down);
		teleop_rows_display = (TextView) findViewById(R.id.teleop_rows_display);

		teleop_columns_up = (Button) findViewById(R.id.teleop_columns_up);
		teleop_columns_down = (Button) findViewById(R.id.teleop_columns_down);
		teleop_columns_display = (TextView) findViewById(R.id.teleop_columns_display);

		teleop_cipher1 = (CheckBox) findViewById(R.id.teleop_cipher1);
		teleop_cipher2 = (CheckBox) findViewById(R.id.teleop_cipher2);

		teleop_relic1_select_zone = (Spinner) findViewById(R.id.teleop_relic1_zone);
		teleop_relic2_select_zone = (Spinner) findViewById(R.id.teleop_relic2_zone);

		teleop_relic1_standing = (CheckBox) findViewById(R.id.teleop_relic1_standing);
		teleop_relic2_standing = (CheckBox) findViewById(R.id.teleop_relic2_standing);

		teleop_balanced = (CheckBox) findViewById(R.id.teleop_balanced);


	}

	public void enableDisableAutonomous (Boolean enabled) {
		autonomous_glyphs_up.setEnabled(enabled);
		autonomous_glyphs_down.setEnabled(enabled);
		autonomous_own_jewel.setEnabled(enabled);
		autonomous_other_jewel.setEnabled(enabled);
		autonomous_key_bonus.setEnabled(enabled);
		autonomous_safe_zone.setEnabled(enabled);

		autonomousGlyphs = 0;
		autonomous_glyphs_display.setText(String.valueOf(autonomousGlyphs));
		autonomous_key_bonus.setChecked(false);
		autonomous_own_jewel.setChecked(false);
		autonomous_other_jewel.setChecked(false);
		autonomous_safe_zone.setChecked(false);
	}
}
