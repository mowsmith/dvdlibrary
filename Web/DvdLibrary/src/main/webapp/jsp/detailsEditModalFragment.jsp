<div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
     aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="detailsModalLabel">DVD Details</h4>
            </div>
            <div class="modal-body">
                <h3 id="dvd-id"></h3>
                <table class="table table-bordered">
                    <tr>
                        <th>Title:</th>
                        <td id="dvd-title"></td>
                    </tr>
                    <tr>
                        <th>Director:</th>
                        <td id="dvd-director"></td>
                    </tr>
                    <tr>
                        <th>Studio:</th>
                        <td id="dvd-studio"></td>
                    </tr>
                    <tr>
                        <th>MPAA Rating:</th>
                        <td id="dvd-mpaaRating"></td>
                    </tr>
                    <tr>
                        <th>Release Date:</th>
                        <td id="dvd-releaseDate"></td>
                    </tr>
                    <tr>
                        <th>User Note:</th>
                        <td id="dvd-userNote"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Close
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
     aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="detailsModalLabel">Edit DVD</h4>
            </div>
            <div class="modal-body">
                <h3 id="dvd-id"></h3>
                <form class="form-horizontal"
                      role="form"
                      action="addNewDvdNoAjax"
                      method="POST">
                    <div class="form-group">
                        <label for="edit-dvd-title"
                               class="col-md-4 control-label">Title:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="edit-dvd-title"
                                   name="title"
                                   placeholder="Title"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-dvd-director" class="col-md-4 control-label">Director:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="edit-dvd-director"
                                   name="director"
                                   placeholder="Director"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-dvd-studio"
                               class="col-md-4 control-label">Studio:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="edit-dvd-studio"
                                   name="studio"
                                   placeholder="Studio"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-dvd-userNote" class="col-md-4 control-label">Note:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="edit-dvd-userNote"
                                   name="userNote"
                                   placeholder="Note"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-dvd-mpaaRating" class="col-md-4 control-label">MPAA Rating: </label>
                        <div class="col-md-8">
                            <input type="radio" name="mpaaRating" value="G" id="G">G
                            <input type="radio" name="mpaaRating" value="PG" id="PG">PG
                            <input type="radio" name="mpaaRating" value="PG13" id="PG13">PG-13
                            <input type="radio" name="mpaaRating" value="R" id="R">R
                            <input type="radio" name="mpaaRating" value="NC17" id="NC17">NC17
                            <input type="radio" name="mpaaRating" value="NR" id="NR">NR<br>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-dvd-releaseDate" class="col-md-4 control-label">Release Date: </label>
                        <div class="col-md-8">
                            <input type="date" name="releaseDate" id="edit-dvd-releaseDate">
                            <c:if test="${''.equals(error)}">${error}</c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="edit-button" class="btn btn-default"
                                    data-dismiss="modal">
                                Edit DVD
                            </button>
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">
                                Cancel
                            </button>
                            <input type="hidden" id="edit-dvd-id">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>