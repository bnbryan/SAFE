package team.ybj.controller;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.TransferRequest;
import team.ybj.service.TransferService;

@RestController
@RequestMapping("transactions")
public class TransferController {

    @Resource
    private TransferService transferService;

    @PostMapping("transfer")
    @ResponseBody
    public ResponseEntity<ResponseResult<Integer>> personalTransfer(@RequestBody TransferRequest transferRequest) {
        int transfer = transferService.transfer(transferRequest);
        if (transfer > 0) {
            ResponseResult<Integer> successResult = new ResponseResult<>(200, "transfer success", transfer);
            return ResponseEntity.ok(successResult);
        } else {
            ResponseResult<Integer> errorResult = new ResponseResult<>(422, "transfer fail", transfer);
            return new ResponseEntity<>(errorResult, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    };

    @GetMapping("test")
    public int test() {
        return 1;
    }

}
