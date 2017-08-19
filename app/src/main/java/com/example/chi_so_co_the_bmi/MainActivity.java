package com.example.chi_so_co_the_bmi;

import java.text.DecimalFormat;

import android.R.color;
import android.os.Bundle;
import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	/*Khai báo control để xử lý */
	Button btnTinhBMI , btnThoat  , btnXoa  ; 
	EditText editTen  , editChieucao , editCannang , editChuandoan   ,  editbmi;
	TextView txtBaoCao ; 
	TableRow tblBaoCao ; 
	String NULL = ""; 
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*Gọi hàm lấy control dựa trên id */
		getControl() ; 

		/*Gọi hàm gắn event cho các control */
		addEvent() ; 		
	}
	
	/*Hàm gắn event cho các control */
	public void addEvent () {

		/* Thiết lập khả năng bắt sự kiện cho btnTinhBMI , btnThoat , btnXoa */
		btnTinhBMI.setOnClickListener( new MyEvent () );
		btnXoa.setOnClickListener(new MyEvent()) ; 
		btnThoat.setOnClickListener(new MyEvent()) ; 
	}
	
	/*Hàm lấy control dựa trên id */
	public  void getControl () {
		
		
		/*Lấy control  từ các id chứa trong  lớp  R.java  */
		btnTinhBMI = (Button) findViewById(R.id.btnTinhBMI) ;
		editTen = (EditText) findViewById(R.id.editTen) ;
		editCannang = (EditText) findViewById(R.id.editCannang) ;
		editChieucao = (EditText) findViewById(R.id.editChieucao);
		editbmi = (EditText) findViewById(R.id.editBMI) ; 
		editChuandoan = (EditText) findViewById(R.id.editChuanDoan) ; 
		txtBaoCao = (TextView) findViewById(R.id.txtBaoCao) ; 
		tblBaoCao = (TableRow) findViewById(R.id.tblBaoCao) ; 
		btnThoat = (Button) findViewById(R.id.btnThoat) ; 
		btnXoa = (Button) findViewById(R.id.btnXoa) ; 
	}
	
	/*Lớp xử lý sự kiện */
	private class MyEvent  implements OnClickListener {

		@Override
		public void onClick(View arg0 ) {
			
			if (arg0 == btnTinhBMI) { //Khi nhấn nút "TínhBMI"
				
				/*Nếu kiểm tra tính  hợp lệ của DL  thì gọi hàm tinhBMI*/
				if (KiemTraDL() == true )
					mtdTinhBMI() ; 
			}
			else if (arg0 == btnXoa) { /*Khi nhấn nút xóa */
				//Gọi hàm xóa dữ liệu //
				mtdXoa() ; 
			}
			else if  (arg0 ==  btnThoat){ /*Khi nhấn nút Thoát */
				//Gọi hàm thoát //
				mtdThoat();
			}
			
		}
		
		/*Hàm tính  BMI*/
		public void mtdTinhBMI () {
			/*Thực thi khi btnTinhBMI được nhấn */
			
			/*
			 * Lấy dữ liệu từ editChieucao và editCannang 
			 * sau đó 
			 * Chuyển dữ liệu sang kiểu double
			 * */ 
			double H = Double.parseDouble(editChieucao.getText()+"");
			double W  =Double.parseDouble(editCannang.getText()+"");
			
			/*Tính chỉ số BMI*/
			double BMI = W/Math.pow(H, 2);
			
			/*Tạo biến "chuandoan" kiểu String để xuất ra editChuandoan*/
			String chuandoan = "" ; 
			
			
			if (BMI  <  18 ) {
				chuandoan = "You are Skinny " ;
			}
			else if (BMI <= 24.9) {
				chuandoan = "You are Normal" ;
			}
			else if (BMI <= 29.9 ) {
				chuandoan = "Obesity Level 1 ";
			}
			else if (BMI <= 34.9) {
				chuandoan =  "Obesity Level 2 ";
			}
			else  {
				chuandoan = "Obesity Level 3 " ;
			}
				
			/*Định dạng dữ liệu với kết quả lấy 1 con số thập phân  */
			DecimalFormat dcf  = new DecimalFormat("#.0") ; 
			editbmi.setText(dcf.format(BMI));
			
			/*Xuất biến  "chuandoan" ra "editChuandoan" */
			editChuandoan.setText(chuandoan);
			
			/*Xuất tên người dùng ra txtBaoCao*/
			txtBaoCao.setText( "Your Result : " + editTen.getText()+"") ;
			
			/*Bảng chuyển màu xanh */
			tblBaoCao.setBackgroundColor(Color.GREEN) ; 
			
		}
		
		/*Hàm xóa dữ liệu */
		public void mtdXoa () {
			
			/*Khi người dùng nhấn nút xóa*/
			/*	Hiện Alert Dialog  xác nhận lại lệnh xóa */
			AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this) ; 
			
			dlg.setTitle("Delete Data  ") ;
			dlg.setMessage("Do you want to Delete Data  ? ") ;
			
			dlg.setPositiveButton("Delete Data ", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					/*Xóa dữ liệu ở 2 ô : editTen , editCannang ,
					 *  editChieucao , editBMI  , editChuandoan*/
					editTen.setText("") ;
					editCannang.setText("") ; 
					editChieucao.setText("") ; 
					editbmi.setText("") ; 
					editChuandoan.setText("") ; 
					
					/*Chuyển focus về editTen */
					editTen.requestFocus() ;
					
					/*Thông báo toast "Xóa thành công "*/
					Toast t = Toast.makeText(MainActivity.this, "Delete Successful ", Toast.LENGTH_SHORT) ;
					
					//Hiện t //
					t.show() ; 
					
				}
			}) ; 
			
			dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				
					dialog.cancel() ; 
					
				}
			}) ; 
			
			//Hiện Alert Dialog //
			dlg.create().show() ; 
			
		}
		
		/*Hàm thoát */
		public void mtdThoat () {
			/* HIện  Alert  Dialog xác nhận lại lệnh thoát  */
			AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this) ; 
			
			dlg.setTitle("Exit ") ;
			dlg.setMessage("Do you want to exit ? ") ;
			
			dlg.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				
					//Thoát ứng dụng //
					finish() ; 
					
				}
			}) ; 
			
			dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				
					dialog.cancel() ; 
					
				}
			}) ;
			
			//Hiện Alert Dialog //
			dlg.create().show();
		}
	}
	
	//Hàm xét hợp lệ của dữ liệu //
	public boolean   KiemTraDL () {
		
		/*Lấy dữ liệu từ editChieucao và editCanang*/
		String strChieucao  = editChieucao.getText()+"" ; 
		String strCannang   = editCannang.getText()+"";
		
		/*Khi cả 2 ô không có chứa dữ liệu */
		if ((strCannang == NULL) && (strChieucao==NULL)  ) {
			
			//Báo lỗi 
			txtBaoCao.setText("Sorry, You didnt enter your \n Height  and  Weight") ;
			
			//Bảng thông báo chuyển màu đỏ 
			tblBaoCao.setBackgroundColor(Color.RED) ; 
			
			//Chuyển focus về editChieucao 
			editChieucao.requestFocus() ; 
			
			return false  ; 
		}
		
		/*Nếu editChieucao rỗng */
		if (strChieucao == NULL) {
			//Báo lỗi 
			txtBaoCao.setText("Sorry, You did'nt enter your Height ") ;
			
			//Bảng thông báo chuyển màu đỏ
			tblBaoCao.setBackgroundColor(Color.RED) ; 
			
			//Chuyển focus về editChieucao 
			editChieucao.requestFocus() ; 
			
			return false  ; 
		}
		/*Nếu editCannang rỗng */
		else if (strCannang == NULL) {
			
			//Báo lỗi 
			txtBaoCao.setText("Sorry, You did'nt enter your Weight") ;
			
			//Bảng thông báo chuyển sang màu đỏ
			tblBaoCao.setBackgroundColor(Color.RED) ; 
			
			//Chuyển focus về  editCannang 
			editCannang.requestFocus() ; 
			
			return false  ; 
		}
		
		return true ; 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
