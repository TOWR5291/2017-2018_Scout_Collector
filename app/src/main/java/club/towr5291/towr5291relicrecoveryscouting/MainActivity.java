package club.towr5291.towr5291relicrecoveryscouting;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.R.color.holo_green_light;

public class MainActivity extends AppCompatActivity {

	boolean autonomousBalanced = false;

	boolean autonomousOwnJewel = false;
	boolean autonomousOtherJewel = false;

	int autonomousGlyphs = 0;
	int autonomousKeys = 0;

	boolean autonomousSafeZone = false;

	int teleopGlyphs = 0;
	int teleopRows = 0;
	int teleopColumns = 0;
	int teleopCiphers = 0;

	int relic1Zone = 0;
	boolean relic1Standing = false;

	int relic2Zone = 0;
	boolean relic2Standing = false;

	boolean teleopBalanced = false;

	int currentScreen = 0; // 0 = Autonomous, 1 = TeleOp, 2 = Comments

	// The Declaration of Things

	ConstraintLayout prematch_layout;
	ConstraintLayout autonomous_layout;
	ConstraintLayout teleop_layout;
	ConstraintLayout comments_layout;
	ConstraintLayout footer_layout;

	EditText prematch_input_scout_name;
	Spinner prematch_select_scout_team;

	Spinner prematch_select_team;
	EditText prematch_input_match;

	Button autonomous_balanced;

	Button autonomous_own_jewel;
	Button autonomous_other_jewel;

	Button autonomous_glyphs;
	Button autonomous_key_bonus;

	Button autonomous_safe_zone;

	Button teleop_glyphs;
	Button teleop_rows;
	Button teleop_columns;
	Button teleop_ciphers;

	Button teleop_relic1_unscored;
	Button teleop_relic1_zone1;
	Button teleop_relic1_zone2;
	Button teleop_relic1_zone3;
	Button teleop_relic1_standing;

	Button teleop_relic2_unscored;
	Button teleop_relic2_zone1;
	Button teleop_relic2_zone2;
	Button teleop_relic2_zone3;
	Button teleop_relic2_standing;

	Button teleop_balanced;

	Button footer_switch_autonomous;
	Button footer_switch_teleop;
	Button footer_switch_comments;

	Button footer_save_reset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		prematch_layout = (ConstraintLayout) findViewById(R.id.prematch_layout);
		autonomous_layout = (ConstraintLayout) findViewById(R.id.autonomous_layout);
		teleop_layout = (ConstraintLayout) findViewById(R.id.teleop_layout);
		comments_layout = (ConstraintLayout) findViewById(R.id.finalize_layout);
		footer_layout = (ConstraintLayout) findViewById(R.id.footer_layout);

		prematch_input_scout_name = (EditText) findViewById(R.id.prematch_input_scout_name);
		prematch_select_scout_team = (Spinner) findViewById(R.id.prematch_select_scout_team);

		prematch_select_team = (Spinner) findViewById(R.id.prematch_select_team);
		prematch_input_match = (EditText) findViewById(R.id.prematch_input_match);

		autonomous_balanced = (Button) findViewById(R.id.autonomous_robot_balanced);

		autonomous_own_jewel = (Button) findViewById(R.id.autonomous_own_jewel);
		autonomous_other_jewel = (Button) findViewById(R.id.autonomous_other_jewel);

		autonomous_glyphs = (Button) findViewById(R.id.autonomous_glyphs);
		autonomous_key_bonus = (Button) findViewById(R.id.autonomous_key_bonus);

		autonomous_safe_zone = (Button) findViewById(R.id.autonomous_safe_zone);

		teleop_glyphs = (Button) findViewById(R.id.teleop_glyphs);
		teleop_rows = (Button) findViewById(R.id.teleop_rows);
		teleop_columns = (Button) findViewById(R.id.teleop_columns);
		teleop_ciphers = (Button) findViewById(R.id.teleop_ciphers);

		teleop_relic1_unscored = (Button) findViewById(R.id.teleop_relic1_unscored);
		teleop_relic1_zone1 = (Button) findViewById(R.id.teleop_relic1_zone1);
		teleop_relic1_zone2 = (Button) findViewById(R.id.teleop_relic1_zone2);
		teleop_relic1_zone3 = (Button) findViewById(R.id.teleop_relic1_zone3);
		teleop_relic1_standing = (Button) findViewById(R.id.teleop_relic1_standing);

		teleop_relic2_unscored = (Button) findViewById(R.id.teleop_relic2_unscored);
		teleop_relic2_zone1 = (Button) findViewById(R.id.teleop_relic2_zone1);
		teleop_relic2_zone2 = (Button) findViewById(R.id.teleop_relic2_zone2);
		teleop_relic2_zone3 = (Button) findViewById(R.id.teleop_relic2_zone3);
		teleop_relic2_standing = (Button) findViewById(R.id.teleop_relic2_standing);

		teleop_balanced = (Button) findViewById(R.id.teleop_balanced);

		footer_switch_autonomous = (Button) findViewById(R.id.footer_switch_autonomous);
		footer_switch_teleop = (Button) findViewById(R.id.footer_switch_teleop);
		footer_switch_comments = (Button) findViewById(R.id.footer_switch_comments);

		footer_save_reset = (Button) findViewById(R.id.footer_save_reset);


		prematch_layout.setVisibility(View.VISIBLE);
		footer_layout.setVisibility(View.VISIBLE);

		setScreen(0);

		enableDisableAutonomous(false);
	}

	public void switchToAutonomous (View view) {
		setScreen(0);
	}

	public void switchToTeleOp (View view) {
		setScreen(1);
	}

	public void switchToComments (View view) {
		setScreen(2);
	}

	public void updateButtons () {

	}

	public void setButtonColor (Button button, boolean onOff) {
		if (!onOff) {
			button.setBackgroundColor(getResources().getColor(R.color.button_off));
		} else {
			button.setBackgroundColor(getResources().getColor(R.color.button_on));
		}
	}

	public void enableDisableAutonomous (Boolean enabled) {
		autonomous_own_jewel.setEnabled(enabled);
		autonomous_other_jewel.setEnabled(enabled);
		autonomous_glyphs.setEnabled(enabled);
		autonomous_key_bonus.setEnabled(enabled);
		autonomous_safe_zone.setEnabled(enabled);

		setButtonColor(autonomous_own_jewel, false);
		setButtonColor(autonomous_other_jewel, false);
		setButtonColor(autonomous_glyphs, false);
		setButtonColor(autonomous_key_bonus, false);
		setButtonColor(autonomous_safe_zone, false);

		autonomousGlyphs = 0;
		autonomousKeys = 0;
	}

	public void setScreen (int screen) {
		if (screen == 0) {
			autonomous_layout.setVisibility(View.VISIBLE);
			teleop_layout.setVisibility(View.GONE);
			comments_layout.setVisibility(View.GONE);

			footer_switch_autonomous.setVisibility(View.GONE);
			footer_switch_teleop.setVisibility(View.VISIBLE);
			footer_switch_comments.setVisibility(View.VISIBLE);
		} else if (screen == 1) {
			autonomous_layout.setVisibility(View.GONE);
			teleop_layout.setVisibility(View.VISIBLE);
			comments_layout.setVisibility(View.GONE);

			footer_switch_autonomous.setVisibility(View.VISIBLE);
			footer_switch_teleop.setVisibility(View.GONE);
			footer_switch_comments.setVisibility(View.VISIBLE);
		} else if (screen == 2) {
			autonomous_layout.setVisibility(View.GONE);
			teleop_layout.setVisibility(View.GONE);
			comments_layout.setVisibility(View.VISIBLE);

			footer_switch_autonomous.setVisibility(View.VISIBLE);
			footer_switch_teleop.setVisibility(View.VISIBLE);
			footer_switch_comments.setVisibility(View.GONE);
		}
	}
}
