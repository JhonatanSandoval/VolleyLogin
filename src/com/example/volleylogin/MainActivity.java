package com.example.volleylogin;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.app.AppController;
import com.example.modelo.Cliente;
import com.example.util.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private TextView txtCorreo;
	private TextView txtClave;
	private Button btnAcceder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtCorreo = (TextView) findViewById(R.id.txtCorreo);
		txtClave = (TextView) findViewById(R.id.txtClave);
		btnAcceder = (Button) findViewById(R.id.btnAcceder);

		btnAcceder.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				doLogin(txtCorreo.getText().toString(), txtClave.getText()
						.toString());
			}
		});

	}

	protected void doLogin(final String correo, final String clave) {

		Log.d("App", "Correo: " + correo);
		Log.d("App", "Clave: " + clave);

		final ProgressDialog pDialog = new ProgressDialog(this);
		pDialog.setMessage("Verificando datos...");
		pDialog.show();

		StringRequest strReq = new StringRequest(Method.POST,
				Constantes.LOGIN_PARTICULAR, new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						pDialog.hide();

						Log.d("App", "Ocultando dialogo");

						try {

							JSONObject json = new JSONObject(response);
							Log.d("App", json.toString());

							Toast.makeText(getApplicationContext(),
									json.getString("mensaje"),
									Toast.LENGTH_SHORT).show();

							boolean success = json.getBoolean("success");

							if (success) {

								Cliente cliente = new Cliente();
								cliente.setIdCliente(json.getString("idCliente"));
								cliente.setCliente(json.getString("cliente"));
								cliente.setCorreo(correo);
								cliente.setCelular(json.getString("celular"));

								Log.d("App", "Datos del cliente");
								Log.d("App",
										"idCliente: " + cliente.getIdCliente());
								Log.d("App", "Cliente: " + cliente.getCliente());
								Log.d("App", "Correo: " + cliente.getCorreo());
								Log.d("App", "Celular: " + cliente.getCelular());

							}

						} catch (JSONException ex) {
							ex.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						VolleyLog.d("App", "Error: " + error.toString());
					}
				}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("correo", correo);
				params.put("clave", clave);
				return params;
			}

		};

		AppController.getInstance().addToRequestQueue(strReq);

	}

}
