package com.lnpdit.policealarm.webservice;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.lnpdit.policealarm.entity.DataDetailInfo;
import com.lnpdit.policealarm.entity.DataInfo;
import com.lnpdit.policealarm.entity.DataInfoDefault;
import com.lnpdit.policealarm.entity.DataInfoUn;
import com.lnpdit.policealarm.entity.LoginUser;
import com.lnpdit.policealarm.utils.EventCache;
import com.lnpdit.policealarm.utils.SOAP_UTILS;
import com.lnpdit.policealarm.webservice.AsyncTaskBase.SoapObjectResult;

public class SoapService implements ISoapService {
    private AsyncTaskBase asynTaskBase = new AsyncTaskBase();
    private SoapRes soapRes = new SoapRes();

    @Override
    public void CheckPoUserLogin(Object[] property_va) {
        String[] property_nm = { "serialNum", "passWd" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.CHECKPOUSERLOGIN);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {

            @Override
            public void soapResult(SoapObject obj) {
                LoginUser loginUser = null;
                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchilds = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildss = (SoapObject) soapchilds.getProperty(0);
                SoapObject soapchildsss = (SoapObject) soapchildss
                        .getProperty(0);
                loginUser = new LoginUser();
                loginUser.setId(soapchildsss.getProperty("Id").toString());
                loginUser.setSerialNum(
                        soapchildsss.getProperty("serialNum").toString());
                loginUser.setPassWd(
                        soapchildsss.getProperty("passWd").toString());
                loginUser.setSex(soapchildsss.getProperty("sex").toString());
                loginUser.setZone(soapchildsss.getProperty("zone").toString());
                loginUser.setCrTime(
                        soapchildsss.getProperty("crTime").toString());

                soapRes.setObj(loginUser);
                soapRes.setCode(SOAP_UTILS.METHOD.CHECKPOUSERLOGIN);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.CHECKPOUSERLOGIN);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    @Override
    public void GetDateList(Object[] property_va, final boolean isPage) {
        String[] property_nm = { "Number", "page" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETDATELIST);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {

            @Override
            public void soapResult(SoapObject obj) {
                try {

                    SoapObject soapchild = (SoapObject) obj.getProperty(0);
                    SoapObject soapchilds = (SoapObject) soapchild
                            .getProperty(1);
                    SoapObject soapchildss = (SoapObject) soapchilds
                            .getProperty(0);
                    List<DataInfo> taglist = new ArrayList<DataInfo>();
                    for (int i = 0; i < soapchildss.getPropertyCount(); i++) {
                        SoapObject soapchildsss = (SoapObject) soapchildss
                                .getProperty(i);
                        DataInfo tagInfo = new DataInfo();
                        tagInfo.setId(
                                soapchildsss.getProperty("Id").toString());
                        tagInfo.setTagName(
                                soapchildsss.getProperty("tagName").toString());
                        tagInfo.setCTime(
                                soapchildsss.getProperty("CTime").toString());
                        taglist.add(tagInfo);
                    }

                    soapRes.setObj(taglist);
                    soapRes.setPage(isPage);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETDATELIST);
                    EventCache.commandActivity.post(soapRes);
                } catch (Exception e) {
                    // TODO: handle exception
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETDATELIST);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETDATELIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    @Override
    public void GetDateListUn(Object[] property_va, final boolean isPage) {
        String[] property_nm = { "Number", "page" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETDATELISTUN);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {

            @Override
            public void soapResult(SoapObject obj) {
                try {

                    SoapObject soapchild = (SoapObject) obj.getProperty(0);
                    SoapObject soapchilds = (SoapObject) soapchild
                            .getProperty(1);
                    SoapObject soapchildss = (SoapObject) soapchilds
                            .getProperty(0);
                    List<DataInfoUn> taglist = new ArrayList<DataInfoUn>();
                    for (int i = 0; i < soapchildss.getPropertyCount(); i++) {
                        SoapObject soapchildsss = (SoapObject) soapchildss
                                .getProperty(i);
                        DataInfoUn tagInfo = new DataInfoUn();
                        tagInfo.setId(
                                soapchildsss.getProperty("Id").toString());
                        tagInfo.setTagName(
                                soapchildsss.getProperty("tagName").toString());
                        tagInfo.setCTime(
                                soapchildsss.getProperty("CTime").toString());
                        taglist.add(tagInfo);
                    }

                    soapRes.setObj(taglist);
                    soapRes.setPage(isPage);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETDATELISTUN);
                    EventCache.commandActivity.post(soapRes);
                } catch (Exception e) {
                    // TODO: handle exception
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETDATELISTUN);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETDATELISTUN);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    @Override
    public void GetDateListDefault(Object[] property_va) {
        String[] property_nm = {};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETDATELISTDEFAULT);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {

            @Override
            public void soapResult(SoapObject obj) {

                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchilds = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildss = (SoapObject) soapchilds.getProperty(0);
                List<DataInfoDefault> taglist = new ArrayList<DataInfoDefault>();
                for (int i = 0; i < soapchildss.getPropertyCount(); i++) {
                    SoapObject soapchildsss = (SoapObject) soapchildss
                            .getProperty(i);
                    DataInfoDefault tagInfo = new DataInfoDefault();
                    tagInfo.setId(soapchildsss.getProperty("Id").toString());
                    tagInfo.setTagName(
                            soapchildsss.getProperty("tagName").toString());
                    tagInfo.setState(
                            soapchildsss.getProperty("state").toString());
                    tagInfo.setCTime(
                            soapchildsss.getProperty("CTime").toString());
                    taglist.add(tagInfo);
                }

                soapRes.setObj(taglist);
                soapRes.setCode(SOAP_UTILS.METHOD.GETDATELISTDEFAULT);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETDATELISTDEFAULT);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    @Override
    public void GetDateDetail(Object[] property_va) {
        String[] property_nm = { "coorId" };
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.GETDATEDETAIL);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {

            @Override
            public void soapResult(SoapObject obj) {

                SoapObject soapchild = (SoapObject) obj.getProperty(0);
                SoapObject soapchilds = (SoapObject) soapchild.getProperty(1);
                SoapObject soapchildss = (SoapObject) soapchilds.getProperty(0);
                SoapObject soapchildsss = (SoapObject) soapchildss
                        .getProperty(0);
                DataDetailInfo tagInfo = new DataDetailInfo();
                tagInfo.setId(soapchildsss.getProperty("Id").toString());
                tagInfo.setTagName(
                        soapchildsss.getProperty("tagName").toString());
                tagInfo.setAdress(soapchildsss.getProperty("adress").toString());
                tagInfo.setCTime(soapchildsss.getProperty("CTime").toString());
                tagInfo.setCoordinateX(soapchildsss.getProperty("coordinateX").toString());
                tagInfo.setCoordinateY(soapchildsss.getProperty("coordinateY").toString());
                tagInfo.setVideoName(soapchildsss.getProperty("videoName").toString());
                String audio_name = soapchildsss.getProperty("audioName").toString();
                tagInfo.setAudioName(audio_name);
                tagInfo.setTextContents (soapchildsss.getProperty("textContents").toString());
                tagInfo.setPic1(soapchildsss.getProperty("pic1").toString());
                tagInfo.setPic2(soapchildsss.getProperty("pic2").toString());
                tagInfo.setPic3(soapchildsss.getProperty("pic3").toString());
                tagInfo.setPic4(soapchildsss.getProperty("pic4").toString());
                tagInfo.setPic5(soapchildsss.getProperty("pic5").toString());
                tagInfo.setPic6(soapchildsss.getProperty("pic6").toString());

                soapRes.setObj(tagInfo);
                soapRes.setCode(SOAP_UTILS.METHOD.GETDATEDETAIL);
                EventCache.commandActivity.post(soapRes);

            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETDATEDETAIL);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    @Override
    public void ChangeDataStatus(Object[] property_va) {
        String[] property_nm = { "coorId","Description"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.CHANGEDATASTATUS);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {

            @Override
            public void soapResult(SoapObject obj) {
                soapRes.setObj(obj.getProperty("ChangeDataStatusResult"));
                System.out.println(">>>>CODE  :　" + obj.toString());
                soapRes.setCode(SOAP_UTILS.METHOD.CHANGEDATASTATUS);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.CHANGEDATASTATUS);
                EventCache.commandActivity.post(soapRes);
            }
        });

    }
    
    @Override
    public void ChangeDataStatusInvalid(Object[] property_va) {
        String[] property_nm = { "coorId","InvalidDescription"};
        asynTaskBase.setMethod(SOAP_UTILS.METHOD.CHANGEDATASTATUSINVALID);
        asynTaskBase.setProperty_nm(property_nm);
        asynTaskBase.setProperty_va(property_va);
        asynTaskBase.executeDo(new SoapObjectResult() {
            
            @Override
            public void soapResult(SoapObject obj) {
                soapRes.setObj(obj.getProperty("ChangeDataStatusInvalidResult"));
                System.out.println(">>>>CODE  :　" + obj.toString());
                soapRes.setCode(SOAP_UTILS.METHOD.CHANGEDATASTATUSINVALID);
                EventCache.commandActivity.post(soapRes);
            }
            
            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.CHANGEDATASTATUSINVALID);
                EventCache.commandActivity.post(soapRes);
            }
        });
        
    }
}
