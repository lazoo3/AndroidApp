package edu.rit.axv3012.spacekid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	Button btnAdi, btnHigh, btnMulti, btnDivide, btnSubtract, btnAbout,
			btnNumFact;
	TextView txtWelcome;
	String enterName;
	EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showInputDialog();
		setContentView(R.layout.activity_main);
		btnAdi = (Button) findViewById(R.id.btnAddit);
		btnHigh = (Button) findViewById(R.id.btnHigh);
		btnMulti = (Button) findViewById(R.id.btnMulti);
		btnDivide = (Button) findViewById(R.id.btnDiv);
		btnSubtract = (Button) findViewById(R.id.btnSubt);
		btnAbout = (Button) findViewById(R.id.btnAbout);
		btnNumFact = (Button) findViewById(R.id.btnNumFact);
		txtWelcome = (TextView) findViewById(R.id.txtWelcome);

		btnAdi.setOnClickListener(this);
		btnHigh.setOnClickListener(this);
		btnMulti.setOnClickListener(this);
		btnDivide.setOnClickListener(this);
		btnSubtract.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
		btnNumFact.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v == btnAdi) {
			Intent intent = new Intent(this, Addition.class);
			enterName = editText.getText().toString();
			intent.putExtra("text", enterName);
			startActivity(intent);
		}
		if (v == btnHigh) {
			Intent intent = new Intent(this, Highscore.class);
			startActivity(intent);
		}
		if (v == btnMulti) {
			Intent intent = new Intent(this, Multiplication.class);
			enterName = editText.getText().toString();
			intent.putExtra("text", enterName);
			startActivity(intent);
		}
		if (v == btnDivide) {
			Intent intent = new Intent(this, Divide.class);
			enterName = editText.getText().toString();
			intent.putExtra("text", enterName);
			startActivity(intent);
		}
		if (v == btnSubtract) {
			Intent intent = new Intent(this, Subtract.class);
			enterName = editText.getText().toString();
			intent.putExtra("text", enterName);
			startActivity(intent);
		}
		if (v == btnAbout) {
			Intent intent = new Intent(this, About.class);
			startActivity(intent);
		}
		if (v == btnNumFact) {
			Intent intent = new Intent(this, NumericFact.class);
			startActivity(intent);
		}

	}

	protected void showInputDialog() {

		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		View promptView = layoutInflater.inflate(R.layout.input_name, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				MainActivity.this);
		alertDialogBuilder.setView(promptView);

		editText = (EditText) promptView.findViewById(R.id.editName);
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						txtWelcome.setText(editText.getText() + "Welcome To SpaceKid");
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		enterName = editText.getText().toString();
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();

	}

}
