package com.th.thuhien.plantshop.ultil;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class DDH {
    private final String NAME_SPACE = "http://tempuri.org/";
    private final String METHOD_NAME = "InsertDDH";
    private final String METHOD_NAME_CT_DDH = "InsertCT_DDH";

    private final String SOAP_ACTION_CT_DDH = NAME_SPACE + METHOD_NAME_CT_DDH;
    private final String SOAP_ACTION = NAME_SPACE + METHOD_NAME;

    private final String URL = "http://plantshop.somee.com/Service.asmx?WSDL";

    public Integer insertDDH(String tenKH, String sdt, String email, String diaChi)
    {
        int result=0;
        try {
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            request.addProperty("tenKH", tenKH);
            request.addProperty("sdt", sdt);
            request.addProperty("email", email);
            request.addProperty("diachi", diaChi);
            envelope.setOutputSoapObject(request);

            httpTransportSE.call(SOAP_ACTION, envelope);
            Object s = envelope.getResponse(); //Get XML Result

            if (s.equals("true")) {
                result = 1;
            }
            else{
                result = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = 0;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            result = 0;
        }catch (Exception e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

//    public Integer insertCT_DDH()
//    {
//        int result=0;
//        try {
//            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
//            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_CT_DDH);
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            envelope.dotNet = true;
//
//            envelope.setOutputSoapObject(request);
//
//            httpTransportSE.call(SOAP_ACTION_CT_DDH, envelope);
//            Object s = envelope.getResponse(); //Get XML Result
//
//            if (s.equals("true")) {
//                result = 1;
//            }
//            else{
//                result = 0;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            result = 0;
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//            result = 0;
//        }catch (Exception e){
//            e.printStackTrace();
//            result = 0;
//        }
//        return result;
//    }
}
