%% Exercise 1: Home heating system

%% States
state('fan off & furnace off').
state('fan on & furnace off').
state('fan off & furnace on').

%% Transitions
transition('fan off & furnace off', 'fan off & furnace on', null, 'room temperature < tr-2', 'turn furnace on').
transition('fan off & furnace on', 'fan off & furnace off', null, 'room temperature >= tr+2', 'turn furnace off').
transition('fan off & furnace on', 'fan on & furnace off', null, 'furnace temperature >= T', 'turn furnace off and fan on').
transition('fan on & furnace off', 'fan off & furnace off', null, 'furnace temperature <= T-5', 'turn fan off').
transition('fan on & furnace off', 'fan off & furnace off', null, 'room temperature >= tr+2', 'turn fan off').

%% Rules
get_events(Ret):- findall(Event, transition(_, _, Event, _, _), L), list_to_set(L, Ret).
get_actions(Ret):- findall(Action, transition(_, _, _, _, Action), L), list_to_set(L, Ret).
path(Destination):- findall(Source, transition(Source, Destination, _, _, _), L), list_to_set(L, Ret), print(Ret).