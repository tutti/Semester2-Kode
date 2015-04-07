(function($) {
    
    var players = {}
    
    var roundTable;
    var standingsTable;
    
    function nameAndRecords(player) {
        return player.name+records(player);
    }
    
    function records(player) {
        return " ("+player.wins+"/"+player.losses+"/"+player.ties+")";
    }
    
    function showRound(roundNum) {
        var round = tournament.rounds[roundNum-1];
        roundTable.empty();
        
        $("#round-number").text(roundNum);
        
        for (g_id in round) {
            var game = round[g_id];
            var player1 = players[game.p1];
            var tr = $("<tr>");
            
            tr.append($("<td>"+nameAndRecords(player1)+"</td>"));
            tr.append($("<td>VS</td>"));
            if (game.p2 == 0) {
                tr.append($('<td colspan="3">Bye</td>'));
            } else {
                var player2 = players[game.p2];
                tr.append($("<td>"+nameAndRecords(player2)+"</td>"));
                if (game.winner) {
                    var winner = players[game.winner];
                    tr.append($("<td>"+winner.name+"</td>"));
                } else if (game.tie) {
                    tr.append($("<td>Tie</td>"));
                } else {
                    tr.append($("<td>Still playing</td>"));
                }
            }
            roundTable.append(tr);
        }
        $("#standings").hide();
        $("#round").show();
    }
    
    function showStandings() {
        $("#standings").show();
        $("#round").hide();
    }
    
    $(document).ready(function() {
        
        roundTable = $("table#round tbody");
        standingsTable = $("table#standings tbody");
        
        console.log(tournament);
        
        for (p_id in tournament.players) {
            players[p_id] = {
                name: tournament.players[p_id],
                wins: 0,
                losses: 0,
                ties: 0,
                owp: 0,
                oowp: 0
            };
        }
        
        var position = 0;
        
        for (r_id in tournament.results) {
            var result = tournament.results[r_id];
            var player = players[result.id];
            
            player.wins = tournament.results[r_id].wins;
            player.losses = tournament.results[r_id].losses;
            player.ties = tournament.results[r_id].ties;
            player.owp = tournament.results[r_id].owp;
            player.oowp = tournament.results[r_id].oowp;
            
            var tr = $("<tr>");
            tr.append($("<td>"+(++position)+"</td>"));
            tr.append($("<td>"+player.name+"</td>"));
            tr.append($("<td>"+records(player)+"</td>"));
            tr.append($("<td>"+player.owp+"%</td>"));
            tr.append($("<td>"+player.oowp+"%</td>"));
            standingsTable.append(tr);
        }
        
        var dropdown = $("#show-round");
        
        for (var i=0; i<tournament.rounds.length; ++i) {
            var round = $('<option value="'+(i+1)+'">Round '+(i+1)+"</option>");
            dropdown.append(round);
            
        }
        
        $("#finished-rounds").text(tournament.rounds.length);
        
        dropdown.change(function(e) {
            var val = $(e.target).val();
            if (val == "standings") {
                showStandings()
            } else {
                showRound(val);
            }
        });
        //$("#show-standings").click(showStandings);
        
        //loadRound(1);
        
        showStandings();
        
    });
    
})(jQuery);