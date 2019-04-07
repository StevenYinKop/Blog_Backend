package xyz.cincommon.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;

@RequiresRoles("admin")
public class AdminController {

}
