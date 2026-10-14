package controller;

import java.util.Scanner;

import service.PhoneService;

public class MainController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--CHƯƠNG TRÌNH QUẢN LÝ ĐIỆN THOẠI --");
            System.out.println("Chọn chức năng theo số (để tiếp tục):");
            System.out.println("1. Thêm mới");
            System.out.println("2. Xóa");
            System.out.println("3. Xem danh sách điện thoại");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");

            String choiceStr = scanner.nextLine().trim();
            switch (choiceStr) {
                case "1":
                    PhoneService.themMoi();
                    break;
                case "2":
                    PhoneService.xoaDienThoai();
                    break;
                case "3":
                    PhoneService.hienThiDanhSach();
                    break;
                case "4":
                    PhoneService.timKiem();
                    break;
                case "5":
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                    System.exit(0);
                default:
                    System.out.println("Chức năng không hợp lệ, vui lòng chọn lại (1 - 5)!");
            }
        }
    }
}
