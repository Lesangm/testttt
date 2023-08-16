package net.smlee.studywebmemo.controllers;

public class ASD {

        case LED_ON:
                if((err = iocatl(fd, IOCTL_NAS_READ_WRITE, &msgtype) < 0))
                {
                        printf("IOCTL_NAS_READ_WRITE error\n");
                }
                printf("call..........run\n");
                break;
        case LED_OFF:
                if((err = ioctl(fd,IOCTL_NAS_READ_WRITE, &msgtype) < 0))
                {
                        printf("IOCTL_NAS_READ_WRITE error\n");
                }
                printf("call..........stop\n");
                break;
        default:
                print_usage();
                break;
}
