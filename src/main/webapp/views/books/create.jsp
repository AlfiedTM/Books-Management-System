<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Books</h2>
    <form name="addBook" id="bookForm">
        <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">Name</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Name">
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput2" class="form-label">Author</label>
            <input type="text" class="form-control" id="exampleFormControlInput2" placeholder="Author">
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput3" class="form-label">Genre</label>
            <input type="text" class="form-control" id="exampleFormControlInput3" placeholder="Genre" required>
        </div>
        <div class="text-center">
            <button class="btn btn-primary" id="save" role="button" >Save</button>
        </div>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $('#save').on('click', function(){
        let formData = new FormData($('#newFile')[0]);
        $.ajax({
            type: 'POST',
            url: '/api/books',
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                if (response.Status == "Success") {
                    swal.fire({
                        padding: "3em",
                        title: 'Success',
                        html: "Hey, <b>Maureen</b>,<br> Your file has been successfully uploaded!",
                        icon: 'success'
                    });
                    $(form).find(':input:not([type="hidden"])').val('');
                } else if (response.Status == "Duplicate") {
                    swal.fire({
                        padding: "3em",
                        title: 'Duplicate File',
                        text: ''+response.Message,
                        icon: 'info'
                    });
                }else {
                    swal.fire({
                        padding: "3em",
                        title: 'Error',
                        text: 'File not successfully uploaded!',
                        icon: 'error'
                    });
                }
                // Handle the success response here
            },
            error: function (error) {
                swal.fire({
                    padding: "3em",
                    title: 'Error',
                    text: 'File upload failed!',
                    icon: 'error'
                });
                // Handle the error response here
            }
        });
    }
    })
</script>
</body>
</html>
