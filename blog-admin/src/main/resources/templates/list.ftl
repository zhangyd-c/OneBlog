<#include "layout/header.ftl"/>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>Projects <small>Listing design</small></h3>
        </div>

        <div class="title_right">
            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                </div>
            </div>
        </div>
    </div>

    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Projects</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Settings 1</a>
                                </li>
                                <li><a href="#">Settings 2</a>
                                </li>
                            </ul>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <p>Simple table with project listing with progress and editing options</p>

                    <!-- start project list -->
                    <table class="table table-striped projects">
                        <thead>
                        <tr>
                            <th style="width: 1%">#</th>
                            <th style="width: 20%">Project Name</th>
                            <th>Team Members</th>
                            <th>Project Progress</th>
                            <th>Status</th>
                            <th style="width: 20%">#Edit</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="57"></div>
                                </div>
                                <small>57% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="/detail" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="47"></div>
                                </div>
                                <small>47% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="77"></div>
                                </div>
                                <small>77% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="60"></div>
                                </div>
                                <small>60% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="12"></div>
                                </div>
                                <small>12% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="35"></div>
                                </div>
                                <small>35% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="87"></div>
                                </div>
                                <small>87% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="77"></div>
                                </div>
                                <small>77% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        <tr>
                            <td>#</td>
                            <td>
                                <a>Pesamakini Backend UI</a>
                                <br />
                                <small>Created 01.01.2015</small>
                            </td>
                            <td>
                                <ul class="list-inline">
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                    <li>
                                        <img src="/assets/images/user.png" class="avatar" alt="Avatar">
                                    </li>
                                </ul>
                            </td>
                            <td class="project_progress">
                                <div class="progress progress_sm">
                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="77"></div>
                                </div>
                                <small>77% Complete</small>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success btn-xs">Success</button>
                            </td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <!-- end project list -->

                </div>
            </div>
        </div>
    </div>
</div>
<#include "layout/footer.ftl"/>