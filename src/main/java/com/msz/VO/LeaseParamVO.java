package com.msz.VO;

import com.msz.model.MszLease;
import com.msz.model.MszLeaseCharge;
import com.msz.model.MszSchedule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class LeaseParamVO {
    @ApiModelProperty(value = "其它收费项", name = "leaseChargeList")
    private List<MszLeaseCharge> leaseChargeList;//其它收费项

    @ApiModelProperty(value = "租约信息", name = "lease")
    private MszLease lease;//租约信息

}
