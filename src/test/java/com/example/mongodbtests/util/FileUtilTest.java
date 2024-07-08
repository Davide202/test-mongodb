package com.example.mongodbtests.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

public class FileUtilTest {

    @Test
    void fromStringToFile() throws IOException {

        String newLine = "\r\n";

        String in = "apim_client_id=1vuS_8FNJss6lqAN4uOpx2f97eIa;apim_client_password=$DigiWorld2022$12;apim_client_secret=1mRSgiUpNS7p3BfLDsKmFI0YihQa;apim_client_username=AE67056;APIM_TOKEN_EP=https://gpg-platform-dev.enelint.global/token;ENRICHED_PLANT_EVENTS=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/meenrichedplantevent/glgt-ap35622mp00071-dev-platform-namespace/80/event-enrich-ms/v1;ENRICHED_PLANT_EVENTS_LOCAL=http://localhost:8082/event-enrich-ms/v1;ENRICHED_PLANT_EVENTS_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/meenrichedplantevent/glgt-ap35622mp00071-dev-platform-namespace/80/event-enrich-ms/v1;ENRICHED_PLANT_EVENTSV3=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/meenrichedplantevent/glgt-ap35622mp00071-dev-platform-namespace/80/event-enrich-ms/v3;EQUIPMENT=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/meplants/glgt-ap35622mp00241-dev-platform-namespace/80/v1/equipments;FILE_MANAGER=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mes3filemanager/glgt-ap35622mp00093-dev-platform-namespace/80/api/v1/pom;FILE_MANAGER_LOCAL=http://localhost:444/file-manager-ms/v1;FILE_MANAGER_MS=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mes3filemanager/glgt-ap35622mp00093-dev-platform-namespace/80/api/v1/pom\\;;FILE_MANAGER_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mes3filemanager/glgt-ap35622mp00093-dev-platform-namespace/80/api/v1/pom;MAIL_MANAGER=http://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mfmailmanager/glgt-ap35622mp00029-dev-platform-namespace/80;MAIL_MANAGER_LOCAL=http://localhost:444;MAIL_MANAGER_MS=http://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mfmailmanager/glgt-ap35622mp00029-dev-platform-namespace/80\\;;MAIL_MANAGER_REMOTE=http://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mfmailmanager/glgt-ap35622mp00029-dev-platform-namespace/80;PDF_REPORT=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mfpdfreport/glgt-ap35622mp00030-dev-platform-namespace/80/report-pdf-ms/v1;PDF_REPORT_LOCAL=http://localhost:444/report-pdf-ms/v1;PDF_REPORT_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mfpdfreport/glgt-ap35622mp00030-dev-platform-namespace/80/report-pdf-ms/v1;PIAF_EVENT_CATEG_LOCAL=http://localhost:444/event-categorization-ms/v1;PIAF_EVENT_CATEG_MS=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mepiafeventcateg/glgt-ap35622mp00031-dev-platform-namespace/80/event-categorization-ms/v1;PIAF_EVENT_CATEG_MS_ENDPOINT_LOCAL=http://localhost:8083/event-categorization-ms/v1;PIAF_EVENT_CATEG_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mepiafeventcateg/glgt-ap35622mp00031-dev-platform-namespace/80/event-categorization-ms/v1;PLATFORM_LOG_LEVEL=DEBUG;SAP_REGISTRY_MS=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mesapassetregistry/glgt-ap35622mp00072-dev-platform-namespace/80/sap-ms;SAP_REGISTRY_MS_LOCAL=http://localhost:8080/sap-ms/v1;SAP_REGISTRY_MS_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mesapassetregistry/glgt-ap35622mp00072-dev-platform-namespace/80/sap-ms/v1;TOMCAT_PORT=8080;WO_MS_PLAT=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/meworkordermgmt/glgt-ap35622mp00174-dev-platform-namespace/80/meworkordermgmt/v4;WO_MS_PLAT_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/meworkordermgmt/glgt-ap35622mp00174-dev-platform-namespace/80/meworkordermgmt/v4";

        String input = "DROPDOWNSET_MS_PLAT=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/memaintendropdownset/glgt-ap35622mp00301-qa-platform-namespace/80/memaintendropdownset/v4;ENRICHED_PLANT_EVENTS=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/meenrichedplantevent/glgt-ap35622mp00071-qa-platform-namespace/80/event-enrich-ms/v1;ENRICHED_PLANT_EVENTS_LOCAL=http://localhost:444/event-enrich-ms/v1;ENRICHED_PLANT_EVENTS_REMOTE=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/meenrichedplantevent/glgt-ap35622mp00071-qa-platform-namespace/80/event-enrich-ms/v1;ENRICHED_PLANT_EVENTSV3=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/meenrichedplantevent/glgt-ap35622mp00071-qa-platform-namespace/80/event-enrich-ms/v3;FILE_MANAGER=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mes3filemanager/glgt-ap35622mp00093-dev-platform-namespace/80/api/v1/pom;FILE_MANAGER_LOCAL=http://localhost:444/file-manager-ms/v1;FILE_MANAGER_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mes3filemanager/glgt-ap35622mp00093-dev-platform-namespace/80/api/v1/pom;MAIL_MANAGER=http://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/mfmailmanager/glgt-ap35622mp00029-qa-platform-namespace/80;MAIL_MANAGER_REMOTE=http://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mfmailmanager/glgt-ap35622mp00029-dev-platform-namespace/80;PDF_REPORT=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/mfpdfreport/glgt-ap35622mp00030-dev-platform-namespace/80/report-pdf-ms/v1;PDF_REPORT_LOCAL=http://localhost:444/report-pdf-ms/v1;PDF_REPORT_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mfpdfreport/glgt-ap35622mp00030-dev-platform-namespace/80/report-pdf-ms/v1;PIAF_EVENT_CATEG_LOCAL=http://localhost:8086/event-categorization-ms/v1;PIAF_EVENT_CATEG_MS=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/mepiafeventcateg/glgt-ap35622mp00031-qa-platform-namespace/80/event-categorization-ms/v1;PIAF_EVENT_CATEG_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/mepiafeventcateg/glgt-ap35622mp00031-dev-platform-namespace/80/event-categorization-ms/v1;PLATFORM_CONTAINER_NAME=local;PLATFORM_LOG_LEVEL=DEBUG;PLATFORM_MODULE_DOMAIN=local-test;PLATFORM_MODULE_ID=local;PLATFORM_MODULE_VERSION=1.0;PLATFORM_NAMESPACE=local;RAW_PLANT_EVENTS_LOCAL=http://localhost:8082/piaf-ms/row-events/v1;RAW_PLANT_EVENTS_REMOTE=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/merawplantevents/glgt-ap35622mp00058-dev-platform-namespace/80;RAW_PLANT_EVENTS_SCHEDULER=https://gpg-platform-dev.enelint.global/Platfrm/UrlRewrite/merawplantevents/glgt-ap35622mp00058-dev-platform-namespace/80/piaf-ms-scheduler;SAP_REGISTRY_MS=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/mesapassetregistry/glgt-ap35622mp00072-qa-platform-namespace/80/sap-ms;WO_MS_PLAT=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/meworkordermgmt/glgt-ap35622mp00174-qa-platform-namespace/80/meworkordermgmt/v4;WONOTIF_MS_PLAT=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/mewonotificationmgmt/glgt-ap35622mp00167-qa-platform-namespace/80/mewonotificationmgmt/v4;WONOTIF_MS_PLAT_REMOTE=https://gpg-platform-qa.enelint.global/Platfrm/UrlRewrite/mewonotificationmgmt/glgt-ap35622mp00167-qa-platform-namespace/80/mewonotificationmgmt/v4";

        String[] lines = input.split(";");

        Files.createDirectories(Path.of("100"));

        Path path = Path.of("100/QA_CONF");
        Files.deleteIfExists(path);
        Files.createFile(path);

        FileWriter writer = new FileWriter(path.toFile());
        for (int i = 0; i < lines.length; i++){
            if (StringUtils.isNotBlank(lines[i])){
                writer.append(newLine+lines[i]);
            }
        }
        writer.close();

        System.out.println("Created file: "+path.toFile().getAbsolutePath());

    }



    @Test
    public void readFile(){
        BufferedReader reader;
        StringJoiner joiner = new StringJoiner("','","('","')");

        try {
            reader = new BufferedReader(new FileReader("src/test/resources/sample.txt"));
            String line = reader.readLine();

            while (line != null) {
//                System.out.println(line);
                // read next line
                joiner.add(line);
                line = reader.readLine();

            }
            System.out.println(joiner.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
