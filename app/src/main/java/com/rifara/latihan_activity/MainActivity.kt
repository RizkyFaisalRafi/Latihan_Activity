package com.rifara.latihan_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
//    beberapa variabel yang akan digunakan untuk menampung View.
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
// Kode di atas mendeklarasikan semua komponen view yang akan dimanipulasi.
// Kita deklarasikan secara global agar bisa dikenal di keseluruhan bagian kelas.

    companion object {
        private const val STATE_RESULT = "state_result"
    }
// onCreate() merupakan metode utama pada activity. Di sinilah kita dapat
// mengatur layout xml. Semua proses inisialisasi komponen yang digunakan
// akan dijalankan di sini. Pada metode ini kita casting semua komponen view
// yang kita telah deklarasikan sebelumnya, agar dapat kita manipulasi.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// menampilkan tampilan yang berasal dari layout activity_main.xml.
        setContentView(R.layout.activity_main)

// EditTextedtWidth disesuaikan (cast) dengan komponen EditText ber-ID
// edt_width di layout activity_main.xml melalui metode findViewById().
//      Kemudian inisiasi variabel (Casting View)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)
// Terakhir
// Pada onCreate inilah kita menggunakan nilai bundle yang telah kita simpan sebelumnya pada onSaveInstanceState.
// Nilai tersebut kita dapatkan dengan menggunakan Key yang sama dengan saat menyimpan, yaitu STATE_RESULT.
// Kemudian kita isikan kembali pada tvResult.
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate){
// Sintaks .text.toString() di atas berfungsi untuk mengambil isi dari
// sebuah EditText kemudian menyimpannya dalam sebuah variabel.
// Tambahan .trim() berfungsi untuk menghiraukan spasi jika ada,
// sehingga nilai yang didapat hanya berupa angka.
// Mengambil value dari EditText
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()


//            val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
//            tvResult.text = volume.toString()


// Cek inputan yang kosong.
// Sintaks .isEmpty() berfungsi untuk mengecek apakah inputan dari Editext
// itu masih kosong. Jika iya, maka kita akan menampilkan pesan error dengan
// menggunakan .setError("Field ini tidak boleh kosong") dan mengganti
// variabel Boolean isEmptyField menjadi true supaya bisa lanjut ke
// proses selanjutnya.
            var isEmptyFields = false
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }
// Menampilkan data ke TextView
// Sintaks !isEmptyFields memiliki arti "jika semua inputan tidak kosong".
// Jika kondisi tersebut terpenuhi, maka langkah selanjutnya yaitu melakukan proses perhitungan.
// Karena yang didapat dari EditText berupa String maka Anda perlu mengkonversinya terlebih dahulu
// dengan cara toDouble(). Langkah terakhir yaitu menampikan hasil perhitungan pada
// TextView tvResult dengan menggunakan .text. Di sini dapat kita lihat bahwa kita perlu merubah
// datanya yang sebelumnya Double menjadi String dengan menggunakan toString()
// karena untuk menampilkan data dengan .text harus berupa String.
            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }

// Pembahasan SaveInstanceState

// Perhatikan metode onSaveInstanceState. Di dalam metode tersebut, hasil perhitungan yang
// ditampilkan pada tvResult dimasukkan pada bundle kemudian disimpan isinya.
// Untuk menyimpan data disini menggunakan konsep Key-Value, dengan STATE_RESULT sebagai
// key dan isi dari tvResult sebagai value. Fungsi onSaveInstanceState akan dipanggil
// secara otomatis sebelum sebuah Activity hancur. Di sini kita perlu menambahkan
// onSaveInstanceState karena ketika orientasi berubah, Activity tersebut akan di-destroy
// dan memanggil fungsi onCreate lagi, sehingga kita perlu menyimpan nilai
// hasil perhitungan tersebut supaya data tetap terjaga dan tidak hilang ketika orientasi berubah.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

}