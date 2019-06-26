function uploadFiles(val) {
  var componentId = dwr.util.getValue("componentId");	
  var image = dwr.util.getValue(val);

  UploadDownload.uploadFiles(image, componentId, val.id, function(data) {
    dwr.util.setValue(val.id +'_hidden', data);
  });
}