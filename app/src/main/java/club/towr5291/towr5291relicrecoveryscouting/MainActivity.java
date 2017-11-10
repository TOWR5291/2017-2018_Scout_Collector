package club.towr5291.towr5291relicrecoveryscouting;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

		// Setting long & short click listeners

		autonomous_balanced.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousBalanced) {
					autonomousBalanced = true;
					updateButtons();
					enableDisableAutonomous(true);
				}
			}
		});

		autonomous_balanced.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousBalanced) {
					autonomousBalanced = false;
					enableDisableAutonomous(false);
				}
				updateButtons();
				return true;
			}
		});

		autonomous_own_jewel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousOwnJewel) {
					autonomousOwnJewel = true;
					updateButtons();
				}
			}
		});

		autonomous_own_jewel.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousOwnJewel) {
					autonomousOwnJewel = false;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_other_jewel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousOtherJewel) {
					autonomousOtherJewel = true;
					updateButtons();
				}
			}
		});

		autonomous_other_jewel.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousOtherJewel) {
					autonomousOtherJewel = false;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_glyphs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				autonomousGlyphs++;
				updateButtons();

			}
		});

		autonomous_glyphs.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousGlyphs != 0) {
					autonomousGlyphs--;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_key_bonus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (autonomousKeys != 2) {
					autonomousKeys++;
				}
				updateButtons();

			}
		});

		autonomous_key_bonus.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousKeys != 0) {
					autonomousKeys--;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_safe_zone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousSafeZone) {
					autonomousSafeZone = true;
					updateButtons();
				}
			}
		});

		autonomous_safe_zone.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousSafeZone) {
					autonomousSafeZone = false;
				}
				updateButtons();
				return true;
			}
		});

		teleop_glyphs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				teleopGlyphs++;
				updateButtons();
			}
		});

		teleop_glyphs.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopGlyphs != 0) {
					teleopGlyphs--;
				}
				updateButtons();
				return true;
			}
		});

		teleop_rows.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (teleopRows != 8) {
					teleopRows++;
				}
				updateButtons();
			}
		});

		teleop_rows.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopRows != 0) {
					teleopRows--;
				}
				updateButtons();
				return true;
			}
		});

		teleop_columns.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (teleopColumns != 6) {
					teleopColumns++;
				}
				updateButtons();
			}
		});

		teleop_columns.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopColumns != 0) {
					teleopColumns--;
				}
				updateButtons();
				return true;
			}
		});

		teleop_ciphers.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (teleopCiphers != 2) {
					teleopCiphers++;
				}
				updateButtons();
			}
		});

		teleop_ciphers.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopCiphers != 0) {
					teleopCiphers--;
				}
				updateButtons();
				return true;
			}
		});

		teleop_relic1_unscored.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic1Zone = 0;
				updateButtons();
			}
		});

		teleop_relic1_zone1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic1Zone = 1;
				updateButtons();
			}
		});

		teleop_relic1_zone2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic1Zone = 2;
				updateButtons();
			}
		});

		teleop_relic1_zone3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic1Zone = 3;
				updateButtons();
			}
		});

		teleop_relic2_unscored.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic2Zone = 0;
				updateButtons();
			}
		});

		teleop_relic2_zone1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic2Zone = 1;
				updateButtons();
			}
		});

		teleop_relic2_zone2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic2Zone = 2;
				updateButtons();
			}
		});

		teleop_relic2_zone3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic2Zone = 3;
				updateButtons();
			}
		});

		teleop_relic1_standing.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!relic1Standing) {
					relic1Standing = true;
					updateButtons();
				}
			}
		});

		teleop_relic1_standing.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (relic1Standing) {
					relic1Standing = false;
				}
				updateButtons();
				return true;
			}
		});

		teleop_relic2_standing.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousSafeZone) {
					autonomousSafeZone = true;
					updateButtons();
				}
			}
		});

		teleop_relic2_standing.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (relic2Standing) {
					relic2Standing = false;
				}
				updateButtons();
				return true;
			}
		});

		teleop_balanced.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!teleopBalanced) {
					teleopBalanced = true;
					updateButtons();
				}
			}
		});

		teleop_balanced.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopBalanced) {
					teleopBalanced = false;
				}
				updateButtons();
				return true;
			}
		});
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

		setButtonColor(autonomous_balanced, autonomousBalanced);

		setButtonColor(autonomous_own_jewel, autonomousOwnJewel);
		setButtonColor(autonomous_other_jewel, autonomousOtherJewel);

		if (autonomousGlyphs != 0) {
			setButtonColor(autonomous_glyphs, true);
			autonomous_glyphs.setText(getResources().getText(R.string.glyphs) + "\n" + autonomousGlyphs);
		} else {
			setButtonColor(autonomous_glyphs, false);
			autonomous_glyphs.setText(getResources().getText(R.string.glyphs) + "\n0");
		}

		if (autonomousKeys != 0) {
			setButtonColor(autonomous_key_bonus, true);
			autonomous_key_bonus.setText(getResources().getText(R.string.autonomous_key_bonus) + "\n" + autonomousKeys);
		} else {
			setButtonColor(autonomous_key_bonus, false);
			autonomous_key_bonus.setText(getResources().getText(R.string.autonomous_key_bonus) + "\n" + autonomousKeys);
		}

		setButtonColor(autonomous_safe_zone, autonomousSafeZone);

		if (teleopGlyphs != 0) {
			setButtonColor(teleop_glyphs, true);
			teleop_glyphs.setText(getResources().getText(R.string.glyphs) + "\n" + teleopGlyphs);
		} else {
			setButtonColor(teleop_glyphs, false);
			teleop_glyphs.setText(getResources().getText(R.string.glyphs) + "\n0");
		}

		if (teleopRows != 0) {
			setButtonColor(teleop_rows, true);
			teleop_rows.setText(getResources().getText(R.string.rows) + "\n" + teleopRows);
		} else {
			setButtonColor(teleop_rows, false);
			teleop_rows.setText(getResources().getText(R.string.rows) + "\n0");
		}

		if (teleopColumns != 0) {
			setButtonColor(teleop_columns, true);
			teleop_columns.setText(getResources().getText(R.string.columns) + "\n" + teleopColumns);
		} else {
			setButtonColor(teleop_columns, false);
			teleop_columns.setText(getResources().getText(R.string.columns) + "\n0");
		}

		if (teleopCiphers != 0) {
			setButtonColor(teleop_ciphers, true);
			teleop_ciphers.setText(getResources().getText(R.string.ciphers) + "\n" + teleopCiphers);
		} else {
			setButtonColor(teleop_ciphers, false);
			teleop_ciphers.setText(getResources().getText(R.string.ciphers) + "\n0");
		}

		Button[] relics1 = {teleop_relic1_unscored, teleop_relic1_zone1, teleop_relic1_zone2, teleop_relic1_zone3};

		for (int i = 0; i <= relic1Zone; i++) {
			setButtonColor(relics1[i], true);
		}

		for (int i = 3; i > relic1Zone; i--) {
			setButtonColor(relics1[i], false);
		}

		setButtonColor(teleop_relic1_standing, relic1Standing);

		Button[] relics2 = {teleop_relic2_unscored, teleop_relic2_zone1, teleop_relic2_zone2, teleop_relic2_zone3};

		for (int i = 0; i < 4; i++) {
			if (i == relic2Zone) {
				setButtonColor(relics2[i], true);
			} else {
				setButtonColor(relics2[i], false);
			}
		}

		setButtonColor(teleop_relic2_standing, relic2Standing);

		setButtonColor(teleop_balanced, teleopBalanced);
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

		autonomousOwnJewel = false;
		autonomousOtherJewel = false;
		autonomousGlyphs = 0;
		autonomousKeys = 0;
		autonomousSafeZone = false;

		updateButtons();
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
