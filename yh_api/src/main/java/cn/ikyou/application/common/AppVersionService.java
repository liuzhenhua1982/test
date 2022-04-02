package cn.ikyou.application.common;

import cn.ikyou.domain.common.entity.TAppVersion;
import cn.ikyou.interfaces.pc.vo.app.PCAppAddReqVO;
import cn.ikyou.interfaces.pc.vo.app.PCAppReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

public interface AppVersionService {
    IPage<TAppVersion> searchPage(PCAppReqVO reqVO);
    boolean save(PCAppAddReqVO reqVO);
}
