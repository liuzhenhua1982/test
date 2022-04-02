package cn.ikyou.interfaces.base.vo.index.indexitem;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class IndexItemUpdateVO extends  IndexItemAddVO{

	private Integer id; 
}