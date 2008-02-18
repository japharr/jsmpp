package org.jsmpp.session.state;

import java.io.IOException;

import org.jsmpp.PDUStringException;
import org.jsmpp.SMPPConstant;
import org.jsmpp.bean.Bind;
import org.jsmpp.bean.Command;
import org.jsmpp.extra.ProcessRequestException;
import org.jsmpp.extra.SessionState;
import org.jsmpp.session.BaseResponseHandler;
import org.jsmpp.session.ServerResponseHandler;
import org.jsmpp.util.DefaultDecomposer;
import org.jsmpp.util.PDUDecomposer;

/**
 * @author uudashr
 *
 */
class SMPPServerSessionOpen implements SMPPServerSessionState {
    private static final PDUDecomposer pduDecomposer = new DefaultDecomposer();
    
    public SessionState getSessionState() {
        return SessionState.OPEN;
    }
    
    public void processBind(Command pduHeader, byte[] pdu,
            ServerResponseHandler responseHandler) throws IOException {
        
        try {
            Bind bind = pduDecomposer.bind(pdu);
            responseHandler.processBind(bind);
            //responseHandler.sendBindResp(BindType.valueOf(pduHeader.getCommandId()), pduHeader.getSequenceNumber());
        } catch (PDUStringException e) {
            responseHandler.sendNegativeResponse(pduHeader.getCommandId(), e.getErrorCode(), pduHeader.getSequenceNumber());
        } catch (IllegalArgumentException e) {
            // FIXME uud: might not need anymore
            responseHandler.sendNegativeResponse(pduHeader.getCommandId(), SMPPConstant.STAT_ESME_RINVCMDID, pduHeader.getSequenceNumber());
        }
    }

    public void processDeliverSmResp(Command pduHeader, byte[] pdu,
            ServerResponseHandler responseHandler) throws IOException {
        throw new IOException("Invalid process for open session state");
    }

    public void processQuerySm(Command pduHeader, byte[] pdu,
            ServerResponseHandler responseHandler)
            throws IOException {
        throw new IOException("Invalid process for open session state");
    }

    public void processSubmitSm(Command pduHeader, byte[] pdu,
            ServerResponseHandler responseHandler)
            throws IOException {
        throw new IOException("Invalid process for open session state");
    }

    public void processEnquireLink(Command pduHeader, byte[] pdu,
            BaseResponseHandler sessionHandler) throws IOException {
        throw new IOException("Invalid process for open session state");
    }

    public void processEnquireLinkResp(Command pduHeader, byte[] pdu,
            BaseResponseHandler sessionHandler) throws IOException {
        throw new IOException("Invalid process for open session state");
    }

    public void processGenericNack(Command pduHeader, byte[] pdu,
            BaseResponseHandler responseHandler) throws IOException {
        throw new IOException("Invalid process for open session state");
    }

    public void processUnbind(Command pduHeader, byte[] pdu,
            BaseResponseHandler sessionHandler) throws IOException {
        throw new IOException("Invalid process for open session state");
    }

    public void processUnbindResp(Command pduHeader, byte[] pdu,
            BaseResponseHandler sessionHandler) throws IOException {
        throw new IOException("Invalid process for open session state");
    }

    public void processUnknownCid(Command pduHeader, byte[] pdu,
            BaseResponseHandler sessionHandler) throws IOException {
        throw new IOException("Invalid process for open session state");
    }
}