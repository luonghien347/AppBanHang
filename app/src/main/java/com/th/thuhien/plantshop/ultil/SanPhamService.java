package com.th.thuhien.plantshop.ultil;

import android.content.Intent;
import android.util.Log;

import com.th.thuhien.plantshop.model.SanPham;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamService {
    private final String NAME_SPACE = "http://tempuri.org/";
    private final String METHOD_NAME_SANPHAM_MOI = "GetListSanPhamMoiNhat";
    private final String METHOD_NAME_SANPHAM_BY_MENU = "GetListSanPhamTheoMenu";
    private final String METHOD_NAME_LIST_SANPHAM = "GetListSanPham";
    private final String METHOD_NAME_SAPXEP_SANPHAM_MOI = "FilterListSanPhamMoiNhat";
    private final String METHOD_NAME_INSERT_SANPHAM = "InsertSanPham ";

    private final String SOAP_ACTION_LIST_SANPHAM_MOI = NAME_SPACE + METHOD_NAME_SANPHAM_MOI;
    private final String SOAP_ACTION_SANPHAM_BY_MENU = NAME_SPACE + METHOD_NAME_SANPHAM_BY_MENU;
    private final String SOAP_ACTION_LIST_SANPHAM = NAME_SPACE + METHOD_NAME_LIST_SANPHAM;
    private final String SOAP_ACTION_SAPXEP_SANPHAM_MOI = NAME_SPACE + METHOD_NAME_SAPXEP_SANPHAM_MOI;
    private final String SOAP_ACTION_INSERT_SANPHAM = NAME_SPACE + METHOD_NAME_INSERT_SANPHAM;

    private final String URL = "http://plantshop.somee.com/SanPhamService.asmx?WSDL";

    public List<SanPham> getListSpMoi(int n){
        List<SanPham> list = new ArrayList<SanPham>();

        SoapObject soapObject = new SoapObject(NAME_SPACE, METHOD_NAME_SANPHAM_MOI);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);

        soapObject.addProperty("n", n);

        MarshalFloat marshal=new MarshalFloat();
        marshal.register(envelope);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION_LIST_SANPHAM_MOI, envelope);

            SoapObject item = (SoapObject) envelope.getResponse();

            for (int i = 0; i < item.getPropertyCount(); i++){
                SoapObject object = (SoapObject) item.getProperty(i);
                SanPham sanPham = new SanPham();
                String maSanPham = object.getProperty("MaSP").toString();
                String tenSanPham = object.getProperty("TenSP").toString();
                String hinhSanPham = object.getProperty("HinhAnh").toString();
                String thongTinSanPham = object.getProperty("ThongTin").toString();
                String giaSanPham = object.getProperty("Gia").toString();
                //String maMenu = object.getProperty("MaMenu").toString();

                sanPham.setMaSp(Integer.parseInt(maSanPham));
                sanPham.setTenSp(tenSanPham);
                sanPham.setHinhAnh(hinhSanPham);
                sanPham.setThongTin(thongTinSanPham);
                sanPham.setGiaSp(Integer.parseInt(giaSanPham));
                //sanPham.setMaMenu(Integer.parseInt(maMenu));

                list.add(sanPham);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<SanPham> getSanPhamByMenu(int mamenu){
        List<SanPham> list = new ArrayList<SanPham>();

        Log.d("ma menu nhan duoc: ", String.valueOf(mamenu));
        SoapObject soapObject = new SoapObject(NAME_SPACE, METHOD_NAME_SANPHAM_BY_MENU);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);

        soapObject.addProperty("mamenu", mamenu);

        MarshalFloat marshal=new MarshalFloat();
        marshal.register(envelope);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION_SANPHAM_BY_MENU, envelope);

            SoapObject item = (SoapObject) envelope.getResponse();

            for (int i = 0; i < item.getPropertyCount(); i++){
                SoapObject object = (SoapObject) item.getProperty(i);
                SanPham sanPham = new SanPham();
                String maSanPham = object.getProperty("MaSP").toString();
                String tenSanPham = object.getProperty("TenSP").toString();
                String hinhSanPham = object.getProperty("HinhAnh").toString();
                String thongTinSanPham = object.getProperty("ThongTin").toString();
                String giaSanPham = object.getProperty("Gia").toString();
                //String maMenu = String.valueOf(mamenu);

                sanPham.setMaSp(Integer.parseInt(maSanPham));
                sanPham.setTenSp(tenSanPham);
                sanPham.setHinhAnh(hinhSanPham);
                sanPham.setThongTin(thongTinSanPham);
                sanPham.setGiaSp(Integer.parseInt(giaSanPham));
                //sanPham.setMaMenu(mamenu);

                list.add(sanPham);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPham> getListSanPham(){

        List<SanPham> list = new ArrayList<>();

        SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_LIST_SANPHAM);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_LIST_SANPHAM, envelope);
            SoapObject item = (SoapObject) envelope.getResponse();

            for (int i = 0; i < item.getPropertyCount(); i++){
                SoapObject object = (SoapObject) item.getProperty(i);
                SanPham sanPham = new SanPham();
                String maSanPham = object.getProperty("MaSP").toString();
                String tenSanPham = object.getProperty("TenSP").toString();
                String hinhSanPham = object.getProperty("HinhAnh").toString();
                String thongTinSanPham = object.getProperty("ThongTin").toString();
                String giaSanPham = object.getProperty("Gia").toString();
//                String maMenu = object.getProperty("MaMenu").toString();

                sanPham.setMaSp(Integer.parseInt(maSanPham));
                sanPham.setTenSp(tenSanPham);
                sanPham.setHinhAnh(hinhSanPham);
                sanPham.setThongTin(thongTinSanPham);
                sanPham.setGiaSp(Integer.parseInt(giaSanPham));
 //               sanPham.setMaMenu(Integer.parseInt(maMenu));

                list.add(sanPham);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<SanPham> sapXepSanPhamMoiNhat(){
        List<SanPham> list = new ArrayList<>();
        SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_SAPXEP_SANPHAM_MOI);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_SAPXEP_SANPHAM_MOI, envelope);
            SoapObject object = (SoapObject) envelope.getResponse();

            for (int i = 0; i < object.getPropertyCount(); i++){
                SoapObject item = (SoapObject) object.getProperty(i);

                SanPham sanPham = new SanPham();

                String maSanPham = item.getProperty("MaSP").toString();
                String tenSanPham = item.getProperty("TenSP").toString();
                String hinhSanPham = item.getProperty("HinhAnh").toString();
                String thongTinSanPham = item.getProperty("ThongTin").toString();
                String giaSanPham = item.getProperty("Gia").toString();
//                String maMenu = item.getProperty("MaMenu").toString();

                sanPham.setMaSp(Integer.parseInt(maSanPham));
                sanPham.setTenSp(tenSanPham);
                sanPham.setHinhAnh(hinhSanPham);
                sanPham.setThongTin(thongTinSanPham);
                sanPham.setGiaSp(Integer.parseInt(giaSanPham));
                //               sanPham.setMaMenu(Integer.parseInt(maMenu));

                list.add(sanPham);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insetSanPham(String name, String img, String info, String price, String type){
        boolean result = false;

        SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_INSERT_SANPHAM);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        int gia = Integer.parseInt(price);
        int mamenu = Integer.parseInt(type);

        request.addProperty("name", name);
        request.addProperty("img", img);
        request.addProperty("info", info);
        request.addProperty("price", gia);
        request.addProperty("type", mamenu);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_INSERT_SANPHAM, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            String a = item.toString();
            result = Boolean.parseBoolean(a);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return result;
    }
}