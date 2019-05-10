package com.th.thuhien.plantshop.ultil;

import android.support.design.widget.NavigationView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class DangNhapService {
    private final String NAME_SPACE = "http://tempuri.org/";
    private final String METHOD_NAME_DANG_NHAP = "KiemTraDangNhap";

    private final String SOAP_ACTION_DANG_NHAP = NAME_SPACE + METHOD_NAME_DANG_NHAP;

    private final String URL = "http://plantshop.somee.com/TaiKhoanService.asmx?WSDL";

    public int kiemTraDangNhap(String username, String password){

        int result = 0;

        SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_DANG_NHAP);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        request.addProperty("user", username);
        request.addProperty("pass", password);

        MarshalFloat marshal = new MarshalFloat();
        marshal.register(envelope);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION_DANG_NHAP, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            String a = item.toString();

            result = Integer.parseInt(a);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return result;
    }
}
