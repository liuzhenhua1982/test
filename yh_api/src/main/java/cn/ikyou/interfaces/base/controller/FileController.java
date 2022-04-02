package cn.ikyou.interfaces.base.controller;

import cn.ikyou.application.business.FileService;
import cn.ikyou.infrastructure.common.HttpStatusCode;
import cn.ikyou.infrastructure.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
@Api(value = "文件中心", tags = "文件中心")
public class FileController {

	private final FileService fileService;

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	@ResponseBody
	@ApiOperation("上传文件")
	@PostMapping("/upload")
	public Result<String> upload(@RequestParam("file") MultipartFile file){
		Result<String> result=new Result<>();
		String path=fileService.upload(file);
		result.setStatus(HttpStatusCode.SUCCESS_CODE);
		result.setMsg("上传成功");
		result.setData(path);
		return result;
	}


	/**
	 * 下载文件
	 * @param fileName
	 */
	@ApiOperation("下载文件")
	@GetMapping("/get/{fileName:.+}")
	public ResponseEntity<byte[]> download(@ApiParam(value="文件名称") @PathVariable("fileName") String fileName){
		try{
			byte[] bytes=fileService.download(fileName);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(bytes,
					httpHeaders, HttpStatus.OK);
		}catch (Exception e){
			log.error(ExceptionUtils.getMessage(e));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
