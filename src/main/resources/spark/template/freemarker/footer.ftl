    <footer class="footer">
        <a href="#">About</a>&nbsp;<a href="#">Mobile</a>&nbsp;<a href="#">Terms</a>&nbsp;<a href="#">Privacy</a>&nbsp;<a href="#">Quick links</a>&nbsp;<a href="#">Routes</a>&nbsp;<a href="#">  Help</a> -
        <strong>eduzi</strong> &copy; Copyright 2015 Ocular-Minds Softwares
    </footer>
    </div>
    <script>
        var colors = {
            "danger-color": "#e74c3c",
            "success-color": "#81b53e",
            "warning-color": "#f0ad4e",
            "inverse-color": "#2c3e50",
            "info-color": "#2d7cb5",
            "default-color": "#6e7882",
            "default-light-color": "#cfd9db",
            "purple-color": "#9D8AC7",
            "mustard-color": "#d4d171",
            "lightred-color": "#e15258",
            "body-bg": "#f6f6f6"
        };
        var config = {
            theme: "eduzi",
            skins: {
                "default": {
                    "primary-color": "#16ae9f"
                },
                "default-nav-inverse": {
                    "color-block": "#242424"
                }
            }
        };
    </script>
    <script src="/js/all.js"></script>
    <script src="/js/app.js"></script>
    <script src="js/upload.js"></script>
    <script src="js/eduzi.js"></script>
    <script>
        $("#btn_timeline").click(function() {

            var t = $("#status").val();
            $.post("/api/move/${user.email}/message", {
                text: t,
                type: "timeline"
            });
        });
    </script>