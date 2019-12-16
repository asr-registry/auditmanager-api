package af.asr.auditmanagerapi.impl;

import af.asr.auditmanagerapi.entity.Audit;
import af.asr.auditmanagerapi.repository.AuditRepository;
import af.asr.auditmanagerapi.request.AuditRequestDto;
import af.asr.auditmanagerapi.util.AuditUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AuditHandler} with function to write
 * {@link AuditRequestDto}
 */
@Service
public class AuditHandlerImpl implements AuditHandler<AuditRequestDto> {

	/**
	 * Field for {@link AuditRepository} having data access operations related to
	 * audit
	 */
	@Autowired
	private AuditRepository auditRepository;

	/**
	 * Field for {@link ModelMapper} for performing object mapping
	 */
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.mosip.kernel.core.audit.handler.AuditHandler#writeAudit(io.mosip.kernel.
	 * core.audit.dto.AuditRequest)
	 */
	@Override
	public boolean addAudit(AuditRequestDto auditRequest) {

		AuditUtils.validateAuditRequest(auditRequest);

		Audit event = modelMapper.map(auditRequest, Audit.class);
		auditRepository.save(event);
		return true;
	}

}