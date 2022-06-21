package com.example.bitmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.bitmap.databinding.ActivityMainBinding;
import com.example.bitmap.modelo.ItemByte;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<ItemByte> lista;
    AdacterDatos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycler.setLayoutManager(new GridLayoutManager(this, 3));

        lista = new ArrayList<ItemByte>();
        for (int i = 1; i <= 128; i++) {
            ItemByte itemByte = new ItemByte("Bitmap " + i, "0", "0");
            lista.add(itemByte);
        }
        AdacterDatos adapter = new AdacterDatos(lista, binding.editBitmap);
        binding.recycler.setAdapter(adapter);

        convertirBit();
    }

    public void convertirBit() {
        binding.buttonAceptar.setOnClickListener(view -> {
        String hexadecimal = binding.editBitmap.getText().toString();

            if(hexadecimal.length() <= 32){
                hexadecimalDecimal(hexadecimal);
//              String hex = String.format("%-32s", hexadecimal).replace(' ','0');
//              binding.editBitmap.setText(hex);
//               Log.e("", "hex: "+hex);
            }
        });
    }

    public void  hexadecimalDecimal(String hexadecimal) {
        final String DIGITOS = "0123456789ABCDEF";
        hexadecimal = hexadecimal.toUpperCase();//convertir todos los caracteres de una cadena dada a mayúsculas.

        String bin = "";
        for (int i = 0; i < hexadecimal.length(); i++) {
            char caracter = hexadecimal.charAt(i);
            int digito = DIGITOS.indexOf(caracter);
            String binary = Integer.toBinaryString(digito);

            while (binary.length() < 4){
                binary = "0" + binary;
            }
            bin = bin + binary;
        }
        Log.e("", "binario: "+bin);
        rellenarCheckbox(bin);
    }

    public void rellenarCheckbox(String bin){
        Log.e("", "binario: "+bin);
            for(int i = 0; i < bin.length(); i++){
                char caracter = bin.charAt(i);
                Log.e("", ""+caracter);
                lista.get(i).setEstado(String.valueOf(caracter));
                lista.get(i).setNuevaCadena("1");
            }
             adapter = new AdacterDatos(lista, binding.editBitmap);
            binding.recycler.setAdapter(adapter);
    }

    public void binarioHexadecimal(String bin){
        int binario = Integer.valueOf(bin);
        int decimal = 0;
        int potencia = 0;
        // Ciclo infinito hasta que binario sea 0
        while (true) {
            if (binario == 0) {
                break;
            } else {
                int temp = binario % 10;
                decimal += temp * Math.pow(2, potencia);
                binario = binario / 10;
                potencia++;
            }
        }
        String num = Integer.toHexString(decimal);
        Log.e("", "hexa: "+num);
        binding.editBitmap.setText(num);
    }


//    public int hexadecimalDecimal(String hexadecimal) {
//        final String DIGITOS = "0123456789ABCDEF";
//        hexadecimal = hexadecimal.toUpperCase();//convertir todos los caracteres de una cadena dada a mayúsculas.
//
//        int decimal = 0;
//        for (int i = 0; i < hexadecimal.length(); i++) {
//            char caracter = hexadecimal.charAt(i);
//            int digito = DIGITOS.indexOf(caracter);
//
//            decimal = 16 * decimal + digito;
//        }
//        return decimal;
//    }

}