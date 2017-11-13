package com.rozenkow.listener;

import com.rozenkow.model.MedicalRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Poul Rozenkow.
 */
@Component
public class MedicalRecordAuditListener extends AbstractMongoEventListener<MedicalRecord> {

  @Override
  public void onBeforeSave(BeforeSaveEvent<MedicalRecord> event) {
    super.onBeforeSave(event);
    updateRecord(event.getSource());
  }

  @Override
  public void onBeforeConvert(BeforeConvertEvent<MedicalRecord> event) {
    super.onBeforeConvert(event);
    updateRecord(event.getSource());
  }

  private void updateRecord(MedicalRecord medicalRecord) {
    if (StringUtils.isBlank(medicalRecord.getId())) {
      medicalRecord.setNumber("" + System.currentTimeMillis()); // todo think of good number generator
      medicalRecord.setId(null);
    }
  }
}
