package edu.rit.axv3012.spacekid;

import edu.rit.axv3012.spacekid.DatabaseConnector;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Highscore extends Activity implements OnClickListener {
	private TextView txtView;
	private Button btnClear;
	DatabaseConnector db;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore_view);
		db = new DatabaseConnector(this);
		btnClear = (Button) findViewById(R.id.btnClear);
		btnClear.setOnClickListener(this);
		txtView = (TextView) findViewById(R.id.txtView);
		txtView.setText("" + db.getAllScores());
	}

	@Override
	public void onClick(View v) {

		db.deleteAllScores();
		finish();
	}
}
