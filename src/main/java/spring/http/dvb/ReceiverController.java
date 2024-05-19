package spring.http.dvb;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/dvb")
@CrossOrigin
public class ReceiverController {
    @GetMapping("/receiver")
    public String startPage(){
        return "dvb/receiver";
    }

    @PostMapping("/receiver")
    public ResponseEntity Gateway(HttpServletResponse response){
        // Устанавливаем HTTP статус 200 OK
        response.setStatus(HttpServletResponse.SC_ACCEPTED);

        // Формируем XML ответ
        String xmlResponse = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> <soap:Body> <TransactionResponse xmlns=\"https://services.elecanet.ru/Payment/\"> <HostResponse operation=\"auth\"> <termType>2</termType> <termID>000122002</termID> <regNum>8980</regNum> <reqDateTime>20080514170000</reqDateTime> <merchantCode>MTS</merchantCode> <respCode>00</respCode> <confirm>0</confirm> </HostResponse> </TransactionResponse> </soap:Body> </soap:Envelope>";

        // Устанавливаем заголовки
        response.setContentType("application/soap+xml; charset=utf-8");
        response.setContentLength(xmlResponse.length());


        // Отправляем XML ответ клиенту
        try {
            System.out.println(response.getStatus());
        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(xmlResponse);
    }
}

